package com.intershop.adapter.payment.partnerpay.internal.service.authorize;

import com.intershop.api.service.payment.v1.capability.Authorize;

/**
 * Describes a factory responsible for the creation of an authorize capability. 
 */
public interface AuthorizeFactory
{
    /**
     * Creates an instance of the authorize capability.
     */
    public Authorize createAuthorize();
}
