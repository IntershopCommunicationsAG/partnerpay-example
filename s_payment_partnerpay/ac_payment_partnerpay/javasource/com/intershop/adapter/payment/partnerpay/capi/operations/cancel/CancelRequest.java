package com.intershop.adapter.payment.partnerpay.capi.operations.cancel;

import com.intershop.api.data.payment.v1.PaymentTransaction;

/**
 * Encapsulates all necessary information to make a cancel request.
 */
public class CancelRequest
{
    private PaymentTransaction paymentTransaction;
    
    /**
     * Sets the transaction that should be cancelled.
     * 
     * @param paymentTransaction the transaction that should be cancelled.
     * 
     * @return this
     */
    public CancelRequest setPaymentTransaction(PaymentTransaction paymentTransaction)
    {
        this.paymentTransaction = paymentTransaction;
        return this;
    }

    /**
     * Returns the transaction that should be cancelled.
     * 
     * @return the transaction that should be cancelled.
     */
    public PaymentTransaction getPaymentTransaction()
    {
        return paymentTransaction;
    }
}
