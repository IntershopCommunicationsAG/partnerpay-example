package com.intershop.adapter.payment.partnerpay.capi.operations.capture;

import com.intershop.api.data.payment.v1.PaymentTransaction;

/**
 * Describes all information that is necessary to make a capture request.
 */
public class CaptureRequest
{
    private PaymentTransaction paymentTransaction;

    /** 
     * Sets the transaction that needs to be captured.
     * 
     * @param paymentTransaction the transaction that needs to be captured.
     * 
     * @return this
     */
    public CaptureRequest setPaymentTransaction(PaymentTransaction paymentTransaction)
    {
        this.paymentTransaction = paymentTransaction;
        return this;
    }

    /**
     * Returns the payment transaction that is associated with this capture request.
     * 
     * @return the payment transaction that is associated with this capture request.
     */
    public PaymentTransaction getPaymentTransaction()
    {
        return paymentTransaction;
    }
}
