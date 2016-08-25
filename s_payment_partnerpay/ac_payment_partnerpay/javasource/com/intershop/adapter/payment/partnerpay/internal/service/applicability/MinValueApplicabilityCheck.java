package com.intershop.adapter.payment.partnerpay.internal.service.applicability;

import java.math.BigDecimal;

import com.intershop.api.data.common.v1.Money;
import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.result.ApplicabilityResult;

public class MinValueApplicabilityCheck implements ApplicabilityCheck
{
    private static final BigDecimal MIN_VALUE = new BigDecimal(30);
    
    public final static String APPLICABILITY_RESTRICTION_MIN_VALUE = "checkout.payment.klarna.applicability.multiple.shipping.notification";

    
    @Override
    public Result<ApplicabilityResult> getApplicability(Payable payable)
    {
        Result<ApplicabilityResult> result = new Result<ApplicabilityResult>(new ApplicabilityResult());

        Money totalGross = payable.getTotals().getGrandTotalGross();
        
        if (totalGross.getValue().compareTo(MIN_VALUE) <= 0)
        {
            result.setState(ApplicabilityResult.RESTRICTED);
            result.getOutput().addRestriction(APPLICABILITY_RESTRICTION_MIN_VALUE);
        }
        else
        {
            result.setState(ApplicabilityResult.APPLICABLE);
        }
        
        return result;
    }

}
