package com.intershop.adapter.payment.partnerpay.capi.operations.cancel;

import com.intershop.adapter.payment.partnerpay.capi.operations.Operation;

/**
 * Describes a cancel operation. A payment can be cancelled before it is captured.
 */
public interface CancelOperation extends Operation<CancelRequest, CancelResponse>
{
    
}
