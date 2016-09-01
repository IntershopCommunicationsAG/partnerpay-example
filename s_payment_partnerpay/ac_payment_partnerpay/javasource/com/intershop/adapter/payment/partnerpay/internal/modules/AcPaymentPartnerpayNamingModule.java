package com.intershop.adapter.payment.partnerpay.internal.modules;

import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.inject.Named;
import javax.inject.Singleton;

import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.intershop.adapter.payment.partnerpay.capi.operations.authorize.AuthorizeOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.cancel.CancelOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.capture.CaptureOperation;
import com.intershop.adapter.payment.partnerpay.capi.service.applicability.ApplicabilityCheck;
import com.intershop.adapter.payment.partnerpay.capi.service.authorize.AuthorizeFactory;
import com.intershop.adapter.payment.partnerpay.capi.service.cancel.CancelFactory;
import com.intershop.adapter.payment.partnerpay.capi.service.capture.CaptureFactory;
import com.intershop.adapter.payment.partnerpay.internal.operations.authorize.AuthorizeOperationImpl;
import com.intershop.adapter.payment.partnerpay.internal.operations.cancel.CancelOperationImpl;
import com.intershop.adapter.payment.partnerpay.internal.operations.capture.CaptureOperationImpl;
import com.intershop.adapter.payment.partnerpay.internal.service.PartnerPayCapabilityProvider;
import com.intershop.adapter.payment.partnerpay.internal.service.PartnerPayPropertyGroupProvider;
import com.intershop.adapter.payment.partnerpay.internal.service.applicability.CombinedApplicabilityCheckImpl;
import com.intershop.adapter.payment.partnerpay.internal.service.applicability.MaxValueApplicabilityCheck;
import com.intershop.adapter.payment.partnerpay.internal.service.applicability.MinValueApplicabilityCheck;
import com.intershop.adapter.payment.partnerpay.internal.service.authorize.AuthorizeFactoryImpl;
import com.intershop.adapter.payment.partnerpay.internal.service.cancel.CancelDateSupplier;
import com.intershop.adapter.payment.partnerpay.internal.service.cancel.CancelFactoryImpl;
import com.intershop.adapter.payment.partnerpay.internal.service.cancel.CancelMaxAllowedTimeSupplier;
import com.intershop.adapter.payment.partnerpay.internal.service.capture.CaptureFactoryImpl;
import com.intershop.adapter.payment.partnerpay.internal.service.config.PartnerPayServiceConfigSupplier;
import com.intershop.api.data.payment.v1.PaymentContext;
import com.intershop.api.service.payment.v1.capability.PaymentCapability;
import com.intershop.beehive.configuration.capi.common.Configuration;
import com.intershop.beehive.core.capi.naming.AbstractNamingModule;

public class AcPaymentPartnerpayNamingModule extends AbstractNamingModule
{
    @Override
    protected void configure()
    {
        // factories for payment capabilities
        // you may replace any factory which means that you can provide
        // your own capability implementations
        bind(AuthorizeFactory.class).to(AuthorizeFactoryImpl.class).in(Singleton.class);
        bind(CaptureFactory.class).to(CaptureFactoryImpl.class).in(Singleton.class);
        bind(CancelFactory.class).to(CancelFactoryImpl.class).in(Singleton.class);
        /**/

        // capability provider, wired with the capability factories
        bind(new TypeLiteral<Function<Class<? extends PaymentCapability>, Optional<PaymentCapability>>>()
        {
        }).annotatedWith(PartnerPayCapabilityProvider.class.getAnnotation(Named.class))
                        .to(PartnerPayCapabilityProvider.class);
                        /**/

        // operations used to connect w/ external providers
        bind(AuthorizeOperation.class).to(AuthorizeOperationImpl.class).in(Singleton.class);
        bind(CaptureOperation.class).to(CaptureOperationImpl.class).in(Singleton.class);
        bind(CancelOperation.class).to(CancelOperationImpl.class).in(Singleton.class);
        /**/

        // Various helper utils which are wired in the
        // implementations. To replace these you need to be familiar with the implementations
        bind(new TypeLiteral<Supplier<Date>>()
        {
        }).annotatedWith(CancelDateSupplier.class.getAnnotation(Named.class)).to(CancelDateSupplier.class)
                        .in(Singleton.class);
        bind(new TypeLiteral<Supplier<Integer>>()
        {
        }).annotatedWith(CancelMaxAllowedTimeSupplier.class.getAnnotation(Named.class))
                        .to(CancelMaxAllowedTimeSupplier.class).in(Singleton.class);
                        /**/

        // Checks for applicability of the payment methods. You may add additional bindings.
        bind(ApplicabilityCheck.class).to(CombinedApplicabilityCheckImpl.class).in(Singleton.class);
        
        Multibinder<ApplicabilityCheck> partnerPayApplicabilityBinder = Multibinder.newSetBinder(binder(),
                        ApplicabilityCheck.class);
        partnerPayApplicabilityBinder.addBinding().to(MinValueApplicabilityCheck.class).in(Singleton.class);
        partnerPayApplicabilityBinder.addBinding().to(MaxValueApplicabilityCheck.class).in(Singleton.class);
        /**/

        // Supplier of payment service configuration
        bind(new TypeLiteral<Supplier<Configuration>>()
        {
        }).annotatedWith(PartnerPayServiceConfigSupplier.class.getAnnotation(Named.class))
                        .to(PartnerPayServiceConfigSupplier.class);
        
        //property groups
        bind(new TypeLiteral<Function<PaymentContext, Collection<Class<?>>>>()
        {
        }).annotatedWith(PartnerPayPropertyGroupProvider.class.getAnnotation(Named.class))
                        .to(PartnerPayPropertyGroupProvider.class);
    }
}
