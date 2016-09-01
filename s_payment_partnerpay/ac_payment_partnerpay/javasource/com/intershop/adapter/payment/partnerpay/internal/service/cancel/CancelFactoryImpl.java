package com.intershop.adapter.payment.partnerpay.internal.service.cancel;

import javax.inject.Inject;

import com.google.inject.Injector;
import com.intershop.adapter.payment.partnerpay.capi.service.cancel.CancelFactory;
import com.intershop.api.service.payment.v1.capability.Cancel;

public class CancelFactoryImpl implements CancelFactory
{
    @Inject
    private Injector injector;
    
    @Override
    public Cancel createCancel()
    {
        Cancel ret = new CancelImpl();
        
        injector.injectMembers(ret);
        
        return ret;
    }

}
