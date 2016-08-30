package com.intershop.adapter.payment.partnerpay.capi.propgroups;

import javax.validation.constraints.NotNull;

import com.intershop.platform.property.capi.annotation.PropertyGroup;

@PropertyGroup
public interface LuckyNumber
{
    @NotNull(message="{payment.partnerpay.pii.error.missing.lucky.number}")
    Integer getLuckyNumber();
}
