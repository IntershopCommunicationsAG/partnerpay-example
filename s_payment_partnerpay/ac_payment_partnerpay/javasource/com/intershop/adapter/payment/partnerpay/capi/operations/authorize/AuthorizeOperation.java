package com.intershop.adapter.payment.partnerpay.capi.operations.authorize;

import com.intershop.adapter.payment.partnerpay.capi.operations.Operation;

/**
 * Describes an authorize operation. 
 * A new payment needs to be authorized before it is captured.  
 */
public interface AuthorizeOperation extends Operation<AuthorizeRequest, AuthorizeResponse>
{
    
}
