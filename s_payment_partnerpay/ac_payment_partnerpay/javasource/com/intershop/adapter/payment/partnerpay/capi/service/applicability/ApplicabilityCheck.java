package com.intershop.adapter.payment.partnerpay.capi.service.applicability;

import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.result.ApplicabilityResult;

/**
 * Describes an applicability check. An implementation may decide if the 
 * payment service is applicable for the given payable. 
 */
public interface ApplicabilityCheck
{
    /**
     * Decides if the payment service is applicable for this payable.
     * 
     * @param payable the payable that should be checked for applicability.
     * 
     * @return the applicability result
     */
    public Result<ApplicabilityResult> getApplicability(Payable payable);
}
