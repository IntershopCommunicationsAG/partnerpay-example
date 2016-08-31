package com.intershop.adapter.payment.partnerpay.internal.service.applicability;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.inject.Inject;

import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.result.ApplicabilityResult;
import com.intershop.beehive.core.capi.log.Logger;

/**
 * A composition of applicability checks. The payable is considered applicable
 * if all checks pass successfully.
 */
public class CombinedApplicabilityCheckImpl implements ApplicabilityCheck
{
    private final Set<ApplicabilityCheck> allApplicabilityChecks;
    
    @Inject
    public CombinedApplicabilityCheckImpl(Set<ApplicabilityCheck> allApplicabilityChecks)
    {
        this.allApplicabilityChecks = new HashSet<>(allApplicabilityChecks);
    }
    
    @Override
    public Result<ApplicabilityResult> getApplicability(Payable payable)
    {        
        Objects.requireNonNull(payable, "Payable can't be null if applicability is calculated.");

        for (ApplicabilityCheck applicabilityCheck : allApplicabilityChecks)
        {
            Result<ApplicabilityResult> applicabilityResult = applicabilityCheck.getApplicability(payable);
            
            if (!ApplicabilityResult.APPLICABLE.equals(applicabilityResult.getState()))
            {
                Logger.debug(this,
                                "PartnerPay Combined Check: {} applicability checks had been applied for payable {} " + 
                                "and at least one failed thus result is {}. The failing check is of class {}.",
                                allApplicabilityChecks.size(), 
                                payable.getHeader().getDocumentInfo().getId(), 
                                applicabilityResult.getState(),
                                applicabilityCheck.getClass().getSimpleName());

                return applicabilityResult;
            }
        }

        
        Logger.debug(this,
                        "Partnerpay Combined Check: {} applicability checks had been applied for payable {} and none failed thus result is applicble.",
                        allApplicabilityChecks.size(), 
                        payable.getHeader().getDocumentInfo().getId());

        return getApplicableResult();
    }

    private Result<ApplicabilityResult> getApplicableResult()
    {
        //an applicable result with no errors
        Result<ApplicabilityResult> applicableResult = new Result<ApplicabilityResult>(new ApplicabilityResult());
        applicableResult.setState(ApplicabilityResult.APPLICABLE);
        return applicableResult;
    }
}
