package com.intershop.adapter.payment.partnerpay.internal.operations.authorize;

import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Named;

import com.intershop.adapter.payment.partnerpay.capi.operations.authorize.AuthorizeOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.authorize.AuthorizeRequest;
import com.intershop.adapter.payment.partnerpay.capi.operations.authorize.AuthorizeResponse;
import com.intershop.adapter.payment.partnerpay.internal.service.config.PartnerPayServiceConfigPrams;
import com.intershop.beehive.configuration.capi.common.Configuration;
import com.intershop.beehive.core.capi.util.UUIDGenerator;

public class AuthorizeOperationImpl implements AuthorizeOperation
{
    @Inject
    @Named("PartnerPay_PartnerPayServiceConfigSupplier")
    private Supplier<Configuration> configSupplier;

    @Inject
    private UUIDGenerator uuidGen;

    @Override
    public AuthorizeResponse execute(AuthorizeRequest req)
    {
        // mimics remote communication. Here normally some REST call or XML-RPC, or something else similar will
        // be called.

        Configuration config = configSupplier.get();

        String password = config.getString(PartnerPayServiceConfigPrams.PASSWORD.getName());

        AuthorizeResponse resp = new AuthorizeResponse();

        if (!"1234".equals(password))
        {
            //fail if the password is not correct
            //please, do not do that at work :-)
            resp.setErrorCode(AuthorizeResponse.EnumErrorCodes.ERROR_AUTH.name());
        }
        else
        {
            Boolean pending = config.getBoolean(PartnerPayServiceConfigPrams.PENDING.getName(), Boolean.FALSE);
            
            resp.setPending(pending);
            resp.setTransactionID(uuidGen.createUUIDString());
        }

        return resp;
    }
}
