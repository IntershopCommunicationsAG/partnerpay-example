package tests.com.intershop.adapter.payment.partnerpay.internal.service.authorize;

import static org.mockito.Mockito.when;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.intershop.adapter.payment.partnerpay.capi.operations.authorize.AuthorizeOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.authorize.AuthorizeResponse;
import com.intershop.adapter.payment.partnerpay.internal.service.authorize.AuthorizeImpl;
import com.intershop.api.data.common.v1.Money;
import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.result.AuthorizationResult;
import com.intershop.beehive.objectgraph.guice.capi.test.MockInjector;
import com.intershop.beehive.objectgraph.guice.capi.test.MockInjector.Bind;

public class AuthorizeImplTest
{
    private static final String TEST_UUID = "I totally understand how batteries feel because "
                    + "I’m rarely ever included in things either.";

    // the rule used to initialize the authorize capability
    @Rule
    public TestRule injection = new MockInjector(this, AuthorizeImpl.class);

    @Mock
    @Bind
    private AuthorizeOperation mockAuthOperation;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private Payable mockPayable;
    
    @Mock
    private Money mockMoney;

    @Inject
    private AuthorizeImpl authorizeToTest;
    
    private AuthorizeResponse testAuthorizeResponse;

    @Before
    public void setUp()
    {
        testAuthorizeResponse = new AuthorizeResponse().setTransactionID(TEST_UUID);
        
        when(mockAuthOperation.execute(Mockito.any())).thenReturn(testAuthorizeResponse);
    }
    
    @Test
    public void testAuthorize_OK()
    {
        Result<AuthorizationResult> result = authorizeToTest.authorize(null, mockPayable, mockMoney);
        
        Assert.assertNotNull(result);
        
        Assert.assertEquals(Result.SUCCESS ,result.getState());
        Assert.assertEquals(TEST_UUID, result.getOutput().getTransactionID());
        Assert.assertEquals(mockMoney, result.getOutput().getTransactionAmount());
    }
    
    @Test
    public void testAuthorize_Pending()
    {
        testAuthorizeResponse.setPending(true);
        
        Result<AuthorizationResult> result = authorizeToTest.authorize(null, mockPayable, mockMoney);
        
        Assert.assertNotNull(result);
        
        Assert.assertEquals(Result.PENDING ,result.getState());
    }
    
    @Test
    public void testAuthorize_Failure()
    {
        testAuthorizeResponse.setErrorCode("I just read a book about Stockholm Syndrome. "
                        + "It was pretty bad at first, but by the end I kinda liked it.");
        
        Result<AuthorizationResult> result = authorizeToTest.authorize(null, mockPayable, mockMoney);
        
        Assert.assertNotNull(result);
        
        Assert.assertEquals(Result.FAILURE, result.getState());
    }
}
