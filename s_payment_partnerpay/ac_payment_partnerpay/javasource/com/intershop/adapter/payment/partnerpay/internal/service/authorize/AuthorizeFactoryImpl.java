package com.intershop.adapter.payment.partnerpay.internal.service.authorize;

import javax.inject.Inject;

import com.google.inject.Injector;
import com.intershop.adapter.payment.partnerpay.capi.service.authorize.AuthorizeFactory;
import com.intershop.api.service.payment.v1.capability.Authorize;

public class AuthorizeFactoryImpl implements AuthorizeFactory
{
    @Inject
    private Injector injector;
    
    @Override
    public Authorize createAuthorize()
    {
        Authorize ret = new AuthorizeImpl();
        
        injector.injectMembers(ret);
        
        return ret;
    }

}
