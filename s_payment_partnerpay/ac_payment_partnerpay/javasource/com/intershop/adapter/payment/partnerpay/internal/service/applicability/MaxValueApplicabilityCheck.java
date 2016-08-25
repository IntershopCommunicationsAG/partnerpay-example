package com.intershop.adapter.payment.partnerpay.internal.service.applicability;

import java.math.BigDecimal;

import com.intershop.api.data.common.v1.Money;
import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.result.ApplicabilityResult;

public class MaxValueApplicabilityCheck implements ApplicabilityCheck
{
    private static final BigDecimal MAX_VALUE = new BigDecimal(200);
    
    @Override
    public Result<ApplicabilityResult> getApplicability(Payable payable)
    {
        Result<ApplicabilityResult> result = new Result<ApplicabilityResult>(new ApplicabilityResult());

        Money totalGross = payable.getTotals().getGrandTotalGross();
        
        if (totalGross.getValue().compareTo(MAX_VALUE) > 0)
        {
            result.setState(ApplicabilityResult.NOT_APPLICABLE);
        }
        else
        {
            result.setState(ApplicabilityResult.APPLICABLE);
        }
        
        return result;
    }

}
