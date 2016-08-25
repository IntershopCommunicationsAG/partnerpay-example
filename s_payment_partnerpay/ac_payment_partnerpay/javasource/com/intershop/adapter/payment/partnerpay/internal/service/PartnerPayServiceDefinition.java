package com.intershop.adapter.payment.partnerpay.internal.service;

import java.util.Arrays;
import java.util.Collection;

import com.intershop.api.service.payment.v1.PaymentService;
import com.intershop.component.service.capi.assignment.ServiceProvider;
import com.intershop.component.service.capi.service.ServiceConfigurationBO;
import com.intershop.component.service.capi.service.ServiceDefinition;

/**
 * The definition of the Partner Pay payment service.
 */
public class PartnerPayServiceDefinition implements ServiceDefinition
{
    @Override
    public Collection<Class<?>> getServiceInterfaces()
    {
        return Arrays.asList(PaymentService.class,  PartnerPayServiceIfc.class);
    }
    
    @Override
    public ServiceProvider getServiceProvider(ServiceConfigurationBO serviceConfigurationBO)
    {
        return new ServiceProvider()
        {
            @Override
            public <T> T getServiceAdapter(Class<T> serviceInterface)
            {
                boolean supported = false;
                for (Class<?> supportedInterface : getServiceInterfaces())
                {
                    if (serviceInterface.isAssignableFrom(supportedInterface))
                    {
                        supported = true;
                        break;
                    }
                }

                if (!supported)
                {
                    throw new IllegalArgumentException("Can't provide an implementation for requested interface: " + serviceInterface.getName());
                }

                PartnerPayServiceImpl result = new PartnerPayServiceImpl(serviceConfigurationBO);
                
                serviceConfigurationBO.getContext().getObjectGraph().injectMembers(result);

                @SuppressWarnings("unchecked")
                T safeResult = (T)result;

                return safeResult;
            }
        };
    }
    
    
}