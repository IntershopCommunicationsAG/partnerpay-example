package com.intershop.adapter.payment.partnerpay.internal.operations.capture;

import java.util.Objects;

import javax.inject.Inject;

import com.intershop.adapter.payment.partnerpay.capi.operations.capture.CaptureOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.capture.CaptureRequest;
import com.intershop.adapter.payment.partnerpay.capi.operations.capture.CaptureResponse;
import com.intershop.api.data.payment.v1.PaymentTransaction;
import com.intershop.beehive.core.capi.log.Logger;
import com.intershop.beehive.core.capi.util.UUIDGenerator;

public class CaptureOperationImpl implements CaptureOperation
{
    @Inject
    private UUIDGenerator uuidGenerator;

    @Override
    public CaptureResponse execute(CaptureRequest req)
    {
        // mimics remote communication. Here normally some REST call or XML-RPC, or something else should similar will
        // be called.

        PaymentTransaction pt = Objects.requireNonNull(req.getPaymentTransaction());

        Logger.debug(this, "Calling remote capture of transaction Id {}", pt.getServiceTransactionId());

        CaptureResponse resp = new CaptureResponse();

        resp.setCaptureID(uuidGenerator.createUUIDString());

        return resp;
    }

}
