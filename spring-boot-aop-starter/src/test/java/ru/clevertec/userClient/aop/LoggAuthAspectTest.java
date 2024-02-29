package ru.clevertec.userClient.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
class LoggAuthAspectTest {

    private LoggAuthAspect loggAuthAspect;
    private ProceedingJoinPoint proceedingJoinPoint;
    private Signature signature;

    @BeforeEach
    public void setup() {
        loggAuthAspect = new LoggAuthAspect();
        proceedingJoinPoint = mock(ProceedingJoinPoint.class);
        signature = mock(Signature.class);
    }

    @Test
    public void testLogMethodExecutionTime() throws Throwable {
        when(proceedingJoinPoint.getSignature()).thenReturn(signature);
        when(signature.getName()).thenReturn("testMethod");
        when(proceedingJoinPoint.proceed()).thenReturn(null);

        Object result = loggAuthAspect.logMethodExecutionTime(proceedingJoinPoint);

        verify(proceedingJoinPoint, times(1)).proceed();
        assertEquals(null, result);
    }

    @Test
    public void testAfterUser() {
        Throwable e = new RuntimeException("Test exception");
        loggAuthAspect.afterUser(e);
    }
}