package com.intershop.adapter.payment.partnerpay.internal.service.authorize;

import com.intershop.api.service.payment.v1.capability.Authorize;
import com.intershop.beehive.core.capi.naming.NamingMgr;

public class AuthorizeFactoryImpl implements AuthorizeFactory
{
    @Override
    public Authorize createAuthorize()
    {
        Authorize ret = new AuthorizeImpl();
        
        NamingMgr.getObjectGraph().injectMembers(ret);
        
        return ret;
    }

}
