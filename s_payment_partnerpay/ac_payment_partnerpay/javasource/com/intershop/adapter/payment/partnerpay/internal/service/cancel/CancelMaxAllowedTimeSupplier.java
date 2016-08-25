package com.intershop.adapter.payment.partnerpay.internal.service.cancel;

import java.util.function.Supplier;

import javax.inject.Named;

@Named("PartnerPay_CancelMaxAllowedTimeSupplier")
public class CancelMaxAllowedTimeSupplier implements Supplier<Integer>
{
    @Override
    public Integer get()
    {
        return 3;
    }
}