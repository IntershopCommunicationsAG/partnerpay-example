package com.intershop.adapter.payment.partnerpay.internal.service.config;

import java.util.Collection;
import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Named;

import com.intershop.adapter.payment.partnerpay.internal.service.PartnerPayServiceIfc;
import com.intershop.beehive.configuration.capi.common.Configuration;
import com.intershop.component.repository.capi.BusinessObjectRepositoryContext;
import com.intershop.component.repository.capi.BusinessObjectRepositoryContextProvider;
import com.intershop.component.service.capi.service.ConfigurationProvider;
import com.intershop.component.service.capi.service.ServiceConfigurationBO;
import com.intershop.component.service.capi.service.ServiceConfigurationBORepository;

@Named("PartnerPay_PartnerPayServiceConfigSupplier")
public class PartnerPayServiceConfigSupplier implements Supplier<Configuration>
{
    @Inject
    protected BusinessObjectRepositoryContextProvider boRepositoryCtxProvider;

    @Override
    public Configuration get()
    {
        ServiceConfigurationBO configBO = getServiceConfigurationBO();

        ConfigurationProvider configProviderExt = configBO.getExtension(ConfigurationProvider.class);
        Configuration configuration = configProviderExt.getConfiguration();

        return configuration;
    }

    private ServiceConfigurationBORepository getServiceConfigurationBORepository()
    {
        BusinessObjectRepositoryContext boRepositoryContext = boRepositoryCtxProvider.getBusinessObjectRepositoryContext();

        ServiceConfigurationBORepository serviceConfigurationBORepository = boRepositoryContext.getRepository(ServiceConfigurationBORepository.EXTENSION_ID);
        return serviceConfigurationBORepository;
    }

    public ServiceConfigurationBO getServiceConfigurationBO()
    {
        ServiceConfigurationBORepository serviceConfigBORepository = getServiceConfigurationBORepository();
        
        Collection<ServiceConfigurationBO> serviceConfigBOs = serviceConfigBORepository.getRunnableServiceConfigurationBOs(PartnerPayServiceIfc.class);

        if (serviceConfigBOs == null || (serviceConfigBOs.size() != 1))
        {
            throw new IllegalStateException(
                "We've found " + (serviceConfigBOs == null ? " no" : serviceConfigBOs.size()) + " " + 
                "PartnerPay services matching the marker interface " + PartnerPayServiceIfc.class + ". Please verify that your configuration is ok! " +
                "We expect only a single service");
        }

        return serviceConfigBOs.iterator().next();
    }
}
