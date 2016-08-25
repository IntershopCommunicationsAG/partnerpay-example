package com.intershop.adapter.payment.partnerpay.capi.operations.capture;

/**
 * Encapsulates an capture response.
 */
public class CaptureResponse{
    
    private String captureId;
   
    /**
     * Sets the ID of the capture performed by PartnerPay.
     * 
     * @param captureId the ID of the capture
     * 
     * @return this
     */
    public CaptureResponse setCaptureID(String captureId)
    {
        this.captureId = captureId;
        return this;
    }
    
    /**
     * Returns the ID of the capture performed by PartnerPay.
     * 
     * @return the ID of the capture performed by PartnerPay.
     */
    public String getCaptureId()
    {
        return captureId;
    }
    
}
