package com.example.aop.apsect.timed;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimedAspect {

    private final Logger logger = LoggerFactory.getLogger(TimedAspect.class);

    @Around("@annotation(timed))")
    public Object run(ProceedingJoinPoint point, Timed timed) throws Throwable {
        try {
            long start = System.currentTimeMillis();
            Object res = point.proceed();
            long end = System.currentTimeMillis();
            long time = end - start;
            logger.info("{} start={} end={} timeTaken={}", timed.description(), start, end, time);
            return res;
        } catch (Throwable e) {
            e.printStackTrace();
            throw e;
        }
    }

}
