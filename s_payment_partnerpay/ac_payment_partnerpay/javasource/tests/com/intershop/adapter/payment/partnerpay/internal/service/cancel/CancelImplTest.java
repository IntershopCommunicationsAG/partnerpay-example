package tests.com.intershop.adapter.payment.partnerpay.internal.service.cancel;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;
import java.util.EnumSet;
import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Named;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import com.intershop.adapter.payment.partnerpay.capi.operations.cancel.CancelOperation;
import com.intershop.adapter.payment.partnerpay.capi.operations.cancel.CancelRequest;
import com.intershop.adapter.payment.partnerpay.internal.service.cancel.CancelImpl;
import com.intershop.api.data.payment.v1.EnumPaymentTransactionStatus;
import com.intershop.api.data.payment.v1.PaymentContext;
import com.intershop.api.data.payment.v1.PaymentHistoryEntry;
import com.intershop.api.data.payment.v1.PaymentTransaction;
import com.intershop.api.service.payment.v1.capability.Authorize;
import com.intershop.beehive.objectgraph.guice.capi.test.MockInjector;
import com.intershop.beehive.objectgraph.guice.capi.test.MockInjector.Bind;

public class CancelImplTest
{
    // the rule used to initialize the cancel capability
    @Rule
    public TestRule injection = new MockInjector(this, CancelImpl.class);

    // bindings within CancelImpl
    @Mock
    @Bind
    @Named("PartnerPay_CancelDateSupplier")
    private Supplier<Date> mockCancelDateSupplier;

    @Mock
    @Bind
    @Named("PartnerPay_CancelMaxAllowedTimeSupplier")
    private Supplier<Integer> mockMaxAllowedTimeSupplier;
    
    @Mock
    @Bind
    private CancelOperation mockCancelOp;

    // the object under test
    @Inject
    private CancelImpl cancelToTest;

    // some mocks to feed the tested object
    @Mock
    private PaymentContext mockPaymentContext;

    @Mock
    private PaymentTransaction mockPaymentTransaction;

    @Mock
    private PaymentHistoryEntry mockPaymentHistoryEntry;

    // some more test objects which seem to be more real
    private final int TEST_MAX_ALLOWED_TIME = 3;

    private Date testDateCreation, testDateWhenBefore3, testDateWhenAfter3, testDateWhen3;

    @Before
    public void setUp()
    {
        when(mockPaymentContext.getPaymentTransaction()).thenReturn(mockPaymentTransaction);
        when(mockPaymentTransaction.getStatus()).thenReturn(EnumPaymentTransactionStatus.AUTHORIZED);

        when(mockMaxAllowedTimeSupplier.get()).thenReturn(TEST_MAX_ALLOWED_TIME);

        when(mockPaymentTransaction.getLatestPaymentHistoryEntry(Authorize.class.getSimpleName()))
                        .thenReturn(mockPaymentHistoryEntry);

        initDates();
    }

    private void initDates()
    {
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(0);
        // -> this is my birthday <-
        time.set(1979, Calendar.MARCH, 7, 0, 0, 0);

        // creation
        testDateCreation = new Date(time.getTimeInMillis());

        time.add(Calendar.HOUR, 2);
        testDateWhenBefore3 = new Date(time.getTimeInMillis());

        time.add(Calendar.HOUR, 1);
        testDateWhen3 = new Date(time.getTimeInMillis());

        time.add(Calendar.HOUR, 2);
        testDateWhenAfter3 = new Date(time.getTimeInMillis());

        // event times
        when(mockPaymentHistoryEntry.getEventTime()).thenReturn(testDateCreation);
    }
    
    @Test
    public void testCancel()
    {
        cancelToTest.cancel(mockPaymentContext, null);
        
        ArgumentCaptor<CancelRequest> requestCaptor = ArgumentCaptor.forClass(CancelRequest.class);
        
        verify(mockCancelOp).execute(requestCaptor.capture());
        
        CancelRequest request = requestCaptor.getValue();
        
        Assert.assertEquals(mockPaymentTransaction, request.getPaymentTransaction());
    }

    @Test
    public void testCanBeCancelled_Dates()
    {
        when(mockCancelDateSupplier.get()).thenReturn(testDateWhenBefore3);
        Assert.assertTrue("The cancel request is less than 3 hours after the creation!", cancelToTest.canBeCancelled(mockPaymentContext));
        
        when(mockCancelDateSupplier.get()).thenReturn(testDateWhenAfter3);
        Assert.assertFalse("The cancel request is more than 3 hours after the creation!", cancelToTest.canBeCancelled(mockPaymentContext));
        
        when(mockCancelDateSupplier.get()).thenReturn(testDateWhen3);
        Assert.assertTrue("The cancel request is 3 hours after the creation!", cancelToTest.canBeCancelled(mockPaymentContext));
    }

    @Test
    public void testCanBeCancelled_Status()
    {
        when(mockPaymentHistoryEntry.getEventTime()).thenReturn(null);
        
        EnumSet<EnumPaymentTransactionStatus> canBeCancelled = EnumSet.of(EnumPaymentTransactionStatus.CREATED,
                        EnumPaymentTransactionStatus.AUTHORIZED);
        EnumSet<EnumPaymentTransactionStatus> cannotBeCancelled = EnumSet.complementOf(canBeCancelled);

        doTestCanBeCancelledStatus(canBeCancelled, true);
        doTestCanBeCancelledStatus(cannotBeCancelled, false);
    }

    private void doTestCanBeCancelledStatus(EnumSet<EnumPaymentTransactionStatus> statuses, boolean expected)
    {
        for (EnumPaymentTransactionStatus status : statuses)
        {
            when(mockPaymentTransaction.getStatus()).thenReturn(status);
            boolean actual = cancelToTest.canBeCancelled(mockPaymentContext);
            Assert.assertEquals(expected, actual);
        }
    }
}
