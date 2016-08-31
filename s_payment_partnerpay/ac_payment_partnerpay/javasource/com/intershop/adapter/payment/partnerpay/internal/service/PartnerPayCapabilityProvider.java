package com.intershop.adapter.payment.partnerpay.internal.service;

import java.util.Optional;
import java.util.function.Function;

import javax.inject.Inject;
import javax.inject.Named;

import com.intershop.adapter.payment.partnerpay.internal.service.authorize.AuthorizeFactory;
import com.intershop.adapter.payment.partnerpay.internal.service.cancel.CancelFactory;
import com.intershop.adapter.payment.partnerpay.internal.service.capture.CaptureFactory;
import com.intershop.api.service.payment.v1.capability.Authorize;
import com.intershop.api.service.payment.v1.capability.Cancel;
import com.intershop.api.service.payment.v1.capability.Capture;
import com.intershop.api.service.payment.v1.capability.PaymentCapability;

/**
 * A component that is responsible for providing payment capabilities.
 * 
 * This implementation is a composition of replaceable capability factories.
 */
@Named("PartnerPay_PartnerPayCapabilityProvider")
public class PartnerPayCapabilityProvider implements Function<Class<? extends PaymentCapability>, Optional<PaymentCapability>>
{
    @Inject 
    private AuthorizeFactory authorizeFactory;
    
    @Inject
    private CaptureFactory captureFactory;
    
    @Inject
    private CancelFactory cancelFactory;
    
    @Override
    public Optional<PaymentCapability> apply(Class<? extends PaymentCapability> t)
    {
        PaymentCapability res = null;
        
        if (t.isAssignableFrom(Authorize.class))
            res = authorizeFactory.createAuthorize();
        else if (t.isAssignableFrom(Capture.class))
            res = captureFactory.createCapture();
        else if (t.isAssignableFrom(Cancel.class))
            res = cancelFactory.createCancel();
        
        return Optional.ofNullable(res);
    }

}
