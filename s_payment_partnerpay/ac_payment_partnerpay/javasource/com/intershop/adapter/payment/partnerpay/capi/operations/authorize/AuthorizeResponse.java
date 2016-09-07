package com.intershop.adapter.payment.partnerpay.capi.operations.authorize;

/**
 * Encapsulates all available information in an authorization response.
 */
public class AuthorizeResponse {

    private String errorCode;
    private boolean pending;
    private String transactionID;
    
    /**
     * Enumerates sample possible error codes.
     */
    public enum EnumErrorCodes
    {
        /**
         * Authentication error occurred.
         */
        ERROR_AUTH
    }
    
    /**
     * Returns an error code if any.
     * 
     * @return an error code if any.
     */
    public String getErrorCode()
    {
        return errorCode;
    }
    
    /**
     * Sets (or clears) an error code.
     * 
     * @param errorCode an error code
     * 
     * @return this
     */
    public AuthorizeResponse setErrorCode(String errorCode)
    {
        this.errorCode = errorCode;
        return this;
    }
    
    /**
     * Returns <code>true</code> if PartnerPay needs some more time to authorize this transaction.
     *  
     * @return <code>true</code> if PartnerPay needs some more time to authorize this transaction.
     */
    public boolean isPending()
    {
        return pending;
    }
    
    /**
     * Sets the pending status of this authorization request.
     * 
     * @param pending if the transaction is pending
     * 
     * @return this
     */
    public AuthorizeResponse setPending(boolean pending)
    {
        this.pending = pending;
        return this;
    }
    
    /**
     * Returns the ID of the transaction as recorded by PartnerPay.
     * 
     * @return the ID of the transaction as recorded by PartnerPay.
     */
    public String getTransactionID()
    {
        return this.transactionID;
    }
    
    /**
     * Sets the transaction ID for this autorization.
     * 
     * @param transactionID  the transaction ID for this autorization.
     * 
     * @return this
     */
    public AuthorizeResponse setTransactionID(String transactionID)
    {
        this.transactionID = transactionID;
        return this;
    }
}
