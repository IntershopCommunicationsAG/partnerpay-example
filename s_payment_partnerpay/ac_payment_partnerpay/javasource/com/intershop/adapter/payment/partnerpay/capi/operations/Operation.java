package com.intershop.adapter.payment.partnerpay.capi.operations;

/**
 * Describes a generic operation which involves remote interaction with the PartnerPay headquarters. 
 * All operations that involve communication with PartnerPay should implement this interface.
 * 
 * @param <Req>
 *            Description of the request
 * @param <Resp>
 *            Description of the response
 */
@FunctionalInterface
public interface Operation<Req, Resp>
{
    /**
     * Executes an operation.
     * 
     * @param req
     *            the request
     * 
     * @return the response returned from the PayPal service
     */
    public Resp execute(Req req);
}
