package com.intershop.adapter.payment.partnerpay.internal.service.applicability;

import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.result.ApplicabilityResult;


public interface ApplicabilityCheck
{
    public Result<ApplicabilityResult> getApplicability(Payable payable);
}
