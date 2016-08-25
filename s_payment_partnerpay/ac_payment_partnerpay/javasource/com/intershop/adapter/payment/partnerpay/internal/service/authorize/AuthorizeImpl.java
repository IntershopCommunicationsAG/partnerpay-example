package com.intershop.adapter.payment.partnerpay.internal.service.authorize;

import java.util.Map;

import javax.inject.Inject;

import com.intershop.adapter.payment.partnerpay.capi.operations.authorize.AuthorizeOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.authorize.AuthorizeRequest;
import com.intershop.adapter.payment.partnerpay.capi.operations.authorize.AuthorizeResponse;
import com.intershop.api.data.common.v1.Money;
import com.intershop.api.data.payment.v1.PaymentContext;
import com.intershop.api.data.payment.v1.PaymentTransaction;
import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.capability.Authorize;
import com.intershop.api.service.payment.v1.result.AuthorizationResult;
import com.intershop.beehive.core.capi.log.Logger;

public class AuthorizeImpl implements Authorize
{
    private static final String AUTH_FAILED_MESSAGE = "payment.partnerpay.authorize.auth.error";

    @Inject
    private AuthorizeOperation authOperation;

    @Override
    public Result<AuthorizationResult> authorize(PaymentContext paymentContext, Payable payable, Money amount)
    {
        Logger.debug(this, "Going to authorize payment for payable {}.", getDebugId(payable));

        Result<AuthorizationResult> result = new Result<AuthorizationResult>(new AuthorizationResult());

        // create the request
        AuthorizeRequest req = new AuthorizeRequest().setPayable(payable);
        // execute the request and inspect the result
        AuthorizeResponse resp = authOperation.execute(req);

        if (resp.getErrorCode() != null)
        {
            result.setState(Result.FAILURE);
            result.addError(resp.getErrorCode(), AUTH_FAILED_MESSAGE);
            Logger.error(this, "Authorization failed! Payable {}.", getDebugId(payable));
        }
        else
        {
            // state
            result.setState(resp.isPending() ? Result.PENDING : Result.SUCCESS);
            // trans ID and amount
            result.getOutput().setTransactionID(resp.getTransactionID());
            result.getOutput().setTransactionAmount(amount);
            // infos
            Logger.debug(this, "Authorization successful. Payable {}, state {}. Transaction ID", getDebugId(payable),
                            result.getState(), result.getOutput().getTransactionID());
        }

        return result;
    }

    @Override
    public Result<AuthorizationResult> onAuthorizeNotification(PaymentContext context, Payable payable,
                    Map<String, Object> parameters)
    {
        Result<AuthorizationResult> result = new Result<AuthorizationResult>(new AuthorizationResult());
        result.setState(Result.SUCCESS);
        
        //normally we should contact the PSP here to verify the status of the transaction.

        PaymentTransaction pt = context.getPaymentTransaction();

        result.getOutput().setTransactionID(pt.getServiceTransactionId());
        result.getOutput().setTransactionAmount(pt.getAmountAuthorized());

        Logger.debug(this, "Asynchronous notification received for payment transaction with service ID {}.",
                        pt.getServiceTransactionId());

        return result;
    }

    private String getDebugId(Payable payable)
    {
        return payable.getHeader().getDocumentInfo().getId();
    }
}
