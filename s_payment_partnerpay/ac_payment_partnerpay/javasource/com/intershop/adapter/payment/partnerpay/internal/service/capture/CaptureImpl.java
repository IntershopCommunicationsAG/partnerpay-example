package com.intershop.adapter.payment.partnerpay.internal.service.capture;

import javax.inject.Inject;

import com.intershop.adapter.payment.partnerpay.capi.operations.capture.CaptureOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.capture.CaptureRequest;
import com.intershop.adapter.payment.partnerpay.capi.operations.capture.CaptureResponse;
import com.intershop.api.data.common.v1.Money;
import com.intershop.api.data.common.v1.changeable.StringAttribute;
import com.intershop.api.data.payment.v1.PaymentContext;
import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.capability.Capture;
import com.intershop.api.service.payment.v1.result.CaptureResult;

public class CaptureImpl implements Capture
{
    @Inject
    private CaptureOperation captureOperation;

    @Override
    public Result<CaptureResult> capture(PaymentContext context, Payable payable, Money captureAmount)
    {
        Result<CaptureResult> res = new Result<CaptureResult>(new CaptureResult());

        // requests and responses
        CaptureRequest captureRequest = new CaptureRequest().setPaymentTransaction(context.getPaymentTransaction());
        CaptureResponse captureResponse = captureOperation.execute(captureRequest);

        // store history
        String captureId = captureResponse.getCaptureId();
        res.getOutput().put(new StringAttribute("CaptureID", captureId));

        res.setState(Result.SUCCESS);

        return res;
    }
}
