package com.intershop.adapter.payment.partnerpay.internal.service.cancel;

import com.intershop.api.service.payment.v1.capability.Cancel;
import com.intershop.beehive.core.capi.naming.NamingMgr;

public class CancelFactoryImpl implements CancelFactory
{
    @Override
    public Cancel createCancel()
    {
        Cancel ret = new CancelImpl();
        
        NamingMgr.getObjectGraph().injectMembers(ret);
        
        return ret;
    }

}
