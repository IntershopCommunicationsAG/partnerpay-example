package tests.com.intershop.adapter.payment.partnerpay.internal.service.applicability;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.intershop.adapter.payment.partnerpay.internal.service.applicability.ApplicabilityCheck;
import com.intershop.adapter.payment.partnerpay.internal.service.applicability.CombinedApplicabilityCheckImpl;
import com.intershop.api.service.common.v1.Result;
import com.intershop.api.service.payment.v1.Payable;
import com.intershop.api.service.payment.v1.result.ApplicabilityResult;
import com.intershop.beehive.objectgraph.guice.capi.test.MockInjector;

public class CombinedApplicabilityCheckImplTest
{
    @Rule
    public TestRule injection = new MockInjector(this, CombinedApplicabilityCheckImpl.class);
    
    @Mock
    private ApplicabilityCheck check1Applicable, check2Applicable, check3NonApplicable;
    
    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private Payable mockPpayable;
    
    private Result<ApplicabilityResult> res1, res2, res3;
    
    @Before
    public void setUp()
    {
        res1 = new Result<>(new ApplicabilityResult());
        res2 = new Result<>(new ApplicabilityResult());
        res3 = new Result<>(new ApplicabilityResult());
        //-=-=-=-
        res1.setState(ApplicabilityResult.APPLICABLE);
        res2.setState(ApplicabilityResult.APPLICABLE);
        res3.setState(ApplicabilityResult.NOT_APPLICABLE);
        //-=-=-=-
        Mockito.when(check1Applicable.getApplicability(mockPpayable)).thenReturn(res1);
        Mockito.when(check2Applicable.getApplicability(mockPpayable)).thenReturn(res2);
        Mockito.when(check3NonApplicable.getApplicability(mockPpayable)).thenReturn(res3);
    }
}
