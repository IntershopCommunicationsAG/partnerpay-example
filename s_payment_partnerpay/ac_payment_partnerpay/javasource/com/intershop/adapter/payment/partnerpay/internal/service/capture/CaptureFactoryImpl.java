package com.intershop.adapter.payment.partnerpay.internal.service.capture;

import com.intershop.api.service.payment.v1.capability.Capture;
import com.intershop.beehive.core.capi.naming.NamingMgr;

public class CaptureFactoryImpl implements CaptureFactory
{
    @Override
    public Capture createCapture()
    {
        Capture ret = new CaptureImpl();
        
        NamingMgr.getObjectGraph().injectMembers(ret);
        
        return ret;
    }

}
