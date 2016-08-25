package com.intershop.adapter.payment.partnerpay.capi.operations.authorize;

import com.intershop.api.service.payment.v1.Payable;

/**
 * Encapsulates all necessary information to make an authorize request.
 */
public class AuthorizeRequest
{
    private Payable payable;

    /**
     * Sets the payable for which this authorize request is.
     * 
     * @param payable
     *            the payable for which payment is requested.
     * 
     * @return this
     * 
     * @see #getPayable()
     */
    public AuthorizeRequest setPayable(Payable payable)
    {
        this.payable = payable;
        return this;
    }

    /**
     * Returns the payable associated with this authorization request.
     * 
     * @return the payable associated with this authorization request.
     */
    public Payable getPayable()
    {
        return payable;
    }
}
