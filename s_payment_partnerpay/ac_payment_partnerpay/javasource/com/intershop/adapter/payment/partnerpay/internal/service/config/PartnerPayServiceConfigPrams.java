package com.intershop.adapter.payment.partnerpay.internal.service.config;

/**
 * Enumerates the configuration parameters of the PartnerPay service. The values of these parameters may be changed by the business user.
 */
public enum PartnerPayServiceConfigPrams
{
    /**
     * The password for the PartnerPay.
     */
    PASSWORD("PartnerPayPaymentService.Password"),
    PENDING("PartnerPayPaymentService.Pending");

    private final String name;

    PartnerPayServiceConfigPrams(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
}
