package com.intershop.adapter.payment.partnerpay.internal.operations.cancel;

import java.util.Objects;

import com.intershop.adapter.payment.partnerpay.capi.operations.cancel.CancelOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.cancel.CancelRequest;
import com.intershop.adapter.payment.partnerpay.capi.operations.cancel.CancelResponse;
import com.intershop.api.data.payment.v1.PaymentTransaction;
import com.intershop.beehive.core.capi.log.Logger;

public class CancelOperationImpl implements CancelOperation
{
    @Override
    public CancelResponse execute(CancelRequest req)
    {
        // mimics remote communication. Here normally some REST call or XML-RPC, or something else should similar will
        // be called.
        
        PaymentTransaction pt = Objects.requireNonNull(req.getPaymentTransaction());
        
        Logger.debug(this, "Calling remote cancellation of transaction with ID {}", pt.getServiceTransactionId());
        
        return new CancelResponse();
    }
    
}
