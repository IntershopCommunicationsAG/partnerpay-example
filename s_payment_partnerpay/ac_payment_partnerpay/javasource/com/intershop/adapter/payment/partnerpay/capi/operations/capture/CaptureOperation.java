package com.intershop.adapter.payment.partnerpay.capi.operations.capture;

import com.intershop.adapter.payment.partnerpay.capi.operations.Operation;

/**
 * Describes a capture operation. A new payment needs to be captured after it is authorized.
 */
public interface CaptureOperation extends Operation<CaptureRequest, CaptureResponse>
{

}
