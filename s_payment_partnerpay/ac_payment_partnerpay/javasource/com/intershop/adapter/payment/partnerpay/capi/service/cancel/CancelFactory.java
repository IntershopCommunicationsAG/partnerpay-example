package com.intershop.adapter.payment.partnerpay.capi.service.cancel;

import com.intershop.api.service.payment.v1.capability.Cancel;

/**
 * Describes a factory that is able to instantiate a cancel capability. 
 */
@FunctionalInterface
public interface CancelFactory
{
    /**
     * Creates a new cancel capability.
     * 
     * @return a new cancel capability.
     */
    public Cancel createCancel();
}
