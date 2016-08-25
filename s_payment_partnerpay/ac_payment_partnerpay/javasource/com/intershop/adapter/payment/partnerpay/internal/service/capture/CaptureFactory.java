package com.intershop.adapter.payment.partnerpay.internal.service.capture;

import com.intershop.api.service.payment.v1.capability.Capture;

/**
 * Describes a factory responsible for the creation of a capture capability. 
 */
public interface CaptureFactory
{
    /**
     * Creates an instance of the capture capability.
     */
    public Capture createCapture();
}
