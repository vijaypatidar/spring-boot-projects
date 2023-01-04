package com.example.aop.apsect.logger;

import java.util.Arrays;
import java.util.stream.Collectors;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("@annotation(log))")
    public Object run(ProceedingJoinPoint point, Log log) throws Throwable {
        logger.info("{} called with args {}, Description = {}", point.getSignature().getName(), Arrays.stream(point.getArgs()).collect(Collectors.toList()), log.description());
        //proceed methods execution
        Object res = point.proceed();
        logger.debug("{} executed successfully : {}", point.getSignature().getName(), log.description());
        return res;
    }

}
