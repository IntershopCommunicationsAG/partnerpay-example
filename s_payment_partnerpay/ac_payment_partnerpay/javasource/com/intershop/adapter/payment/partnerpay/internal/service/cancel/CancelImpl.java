package com.intershop.adapter.payment.partnerpay.internal.service.cancel;

import java.time.Duration;
import java.util.Date;
import java.util.Optional;
import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Named;

import com.intershop.adapter.payment.partnerpay.capi.operations.cancel.CancelOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.cancel.CancelRequest;
import com.intershop.api.data.payment.v1.PaymentContext;
import com.intershop.api.data.payment.v1.PaymentTransaction;
import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.capability.Authorize;
import com.intershop.api.service.payment.v1.capability.Cancel;
import com.intershop.api.service.payment.v1.result.CancelResult;
import com.intershop.beehive.core.capi.log.Logger;

public class CancelImpl implements Cancel
{
    @Inject
    @Named("PartnerPay_CancelDateSupplier")
    private Supplier<Date> cancelDateSupplier;

    @Inject
    @Named("PartnerPay_CancelMaxAllowedTimeSupplier")
    private Supplier<Integer> maxAllowedTimeSupplier;

    @Inject
    private CancelOperation cancelOp;

    @Override
    public Result<CancelResult> cancel(PaymentContext context, Payable payable)
    {
        Result<CancelResult> cancelResult = new Result<CancelResult>(new CancelResult());

        Logger.debug(this, "Cancellation triggered for payment transaction {}",
                        context.getPaymentTransaction().getId());

        // execute 'remote' action
        cancelOp.execute(new CancelRequest().setPaymentTransaction(context.getPaymentTransaction()));

        return cancelResult;
    }

    @Override
    public boolean canBeCancelled(PaymentContext context)
    {
        if (Cancel.super.canBeCancelled(context))
        {
            return isFresh(context.getPaymentTransaction(), cancelDateSupplier.get());
        }
        else
        {
            return false;
        }
    }

    private boolean isFresh(PaymentTransaction pt, Date when)
    {
        Optional<Date> optCreated = Optional
                        .ofNullable(pt.getLatestPaymentHistoryEntry(Authorize.class.getSimpleName()))
                        .map(ph -> ph.getEventTime());

        if (!optCreated.isPresent())
        {
            Logger.error(this,
                            "There is no authorize payment history for this payment transaction {}. "
                                            + "This should not normally happen. "
                                            + "We will consider that a cancel attempt can be made though.",
                            pt.getId());
            return true;
        }
        else
        {
            Date created = optCreated.get();
            Date now = when;

            long hours = Duration.between(created.toInstant(), now.toInstant()).toHours();

            boolean result = hours <= maxAllowedTimeSupplier.get();

            Logger.debug(this,
                            "The payment transaction is {} hours old. Max allowed timeout is {}. Can be cancelled {}",
                            pt.getId(), maxAllowedTimeSupplier.get(), result);

            return result;
        }
    }
}
