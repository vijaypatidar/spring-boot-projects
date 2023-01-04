package com.example.aop.apsect.retryable;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class RetryAspect {

    private final Logger logger = LoggerFactory.getLogger(RetryAspect.class);

    @Around("@annotation(retry))")
    public Object run(ProceedingJoinPoint point, Retry retry) throws Throwable {
        int retrycount = retry.retryCount();
        Throwable exp = null;
        do {
            try {
                if (retry.retryCount()>retrycount){
                    logger.info("Retry count:{}",(retry.retryCount()-retrycount));
                }
                return point.proceed();
            } catch (Throwable e) {
                if (retry.exception() != e.getClass()) {
                    logger.error(e.getMessage());
                    throw e;
                }
                exp = e;
            }
            retrycount--;
        } while (retrycount >= 0);
        throw exp;
    }

}
