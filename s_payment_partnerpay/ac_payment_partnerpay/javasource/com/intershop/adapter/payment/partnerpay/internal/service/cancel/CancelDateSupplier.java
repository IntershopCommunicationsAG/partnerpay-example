package com.intershop.adapter.payment.partnerpay.internal.service.cancel;

import java.util.Date;
import java.util.function.Supplier;

import javax.inject.Named;

@Named("PartnerPay_CancelDateSupplier")
public class CancelDateSupplier implements Supplier<Date>
{
    @Override
    public Date get()
    {
        return new Date();
    }
}