package com.example.aop.apsect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

@Component
@Aspect
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("@annotation(loggable))")
    public Object run(ProceedingJoinPoint point, Loggable loggable) throws Throwable {
        try {
            //save log some where in persist db
            logger.info(
                    point.getSignature().getName() + " called with args " + Arrays.stream(point.getArgs()).collect(Collectors.toList()) + ", Description = " + loggable.description()
            );
            //proceed methods execution
            Object res = point.proceed();
            logger.debug(
                    point.getSignature().getName() + " executed successfully : " + loggable.description()
            );
            return res;
        } catch (Throwable e) {
            logger.error(
                    point.getSignature().getName() + " execution failed : " + e.getMessage()
            );
            e.printStackTrace();
            throw e;
        }
    }

}
