package com.intershop.adapter.payment.partnerpay.internal.service.capture;

import javax.inject.Inject;

import com.google.inject.Injector;
import com.intershop.adapter.payment.partnerpay.capi.service.capture.CaptureFactory;
import com.intershop.api.service.payment.v1.capability.Capture;

public class CaptureFactoryImpl implements CaptureFactory
{
    @Inject
    private Injector injector;
    
    @Override
    public Capture createCapture()
    {
        Capture ret = new CaptureImpl();
        
        injector.injectMembers(ret);
        
        return ret;
    }

}
