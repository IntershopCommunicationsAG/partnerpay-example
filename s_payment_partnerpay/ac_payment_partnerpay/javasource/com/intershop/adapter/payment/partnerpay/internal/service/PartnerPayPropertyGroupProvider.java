package com.intershop.adapter.payment.partnerpay.internal.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

import javax.inject.Named;

import com.intershop.adapter.payment.partnerpay.capi.propgroups.LuckyNumber;
import com.intershop.api.data.payment.v1.PaymentContext;

@Named("PartnerPay_PartnerPayPropertyGroupProvider")
public class PartnerPayPropertyGroupProvider implements Function<PaymentContext, Collection<Class<?>>>
{
    @Override
    public Collection<Class<?>> apply(PaymentContext t)
    {
        return Arrays.asList(LuckyNumber.class);
    }
}
