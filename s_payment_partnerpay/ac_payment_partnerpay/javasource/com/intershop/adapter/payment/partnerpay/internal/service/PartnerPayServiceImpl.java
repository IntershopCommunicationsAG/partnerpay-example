package com.intershop.adapter.payment.partnerpay.internal.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

import javax.inject.Inject;
import javax.inject.Named;

import com.intershop.adapter.payment.partnerpay.internal.service.applicability.ApplicabilityCheck;
import com.intershop.api.data.payment.v1.PaymentContext;
import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.PaymentService;
import com.intershop.api.service.payment.v1.capability.PaymentCapability;
import com.intershop.api.service.payment.v1.result.ApplicabilityResult;
import com.intershop.component.service.capi.service.ServiceConfigurationBO;

/**
 * A class that represents the PartnerPay payment service.
 */
public class PartnerPayServiceImpl implements PaymentService, PartnerPayServiceIfc
{
    @Inject
    @Named("PartnerPay_PartnerPayCapabilityProvider")
    private Function<Class<? extends PaymentCapability>, Optional<PaymentCapability>> capabilityProvider;
    
    @Inject
    private ApplicabilityCheck applicabilityCheck;
    
    public PartnerPayServiceImpl(ServiceConfigurationBO serviceConfigurationBO)
    {
    }

    @Override
    public Result<ApplicabilityResult> getApplicability(Payable payable)
    {
        return applicabilityCheck.getApplicability(payable);
    }

    @Override
    public <T extends PaymentCapability> T getCapability(Class<T> capabilityClazz)
    {
        @SuppressWarnings("unchecked")
        T capability = (T)capabilityProvider.apply(capabilityClazz).orElse(null);
        
        return capability;
    }

    @Override
    public String getID()
    {
        return PartnerPayServiceIfc.SERVICE_ID;
    }

    @Override
    public Collection<Class<?>> getPaymentParameterDescriptors(PaymentContext context)
    {
        return Collections.emptyList();
    }
}
