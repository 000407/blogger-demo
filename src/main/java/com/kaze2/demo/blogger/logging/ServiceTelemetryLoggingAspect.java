package com.kaze2.demo.blogger.logging;

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
public class ServiceTelemetryLoggingAspect {
    @Around("execution(* com.kaze2.demo.blogger.service..*.* (..))")
    public Object logBeforeAndAfterServiceMethods(ProceedingJoinPoint pjp) throws Throwable {
        try {
            log.info("{} has started execution.", pjp.getSignature());
            Object resultOfMethodCall = pjp.proceed();
            log.info("{} finished execution with result: {}", pjp.getSignature(), resultOfMethodCall);
            return resultOfMethodCall;
        } catch (Throwable e) {
            log.error(e);
            throw e;
        }
    }
}
