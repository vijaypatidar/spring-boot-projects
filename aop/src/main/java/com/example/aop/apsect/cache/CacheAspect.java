package com.example.aop.apsect.cache;

import com.example.aop.services.CacheService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CacheAspect {

    @Autowired
    private CacheService cacheService;

    @Around("@annotation(cache))")
    public Object run(ProceedingJoinPoint point, Cache cache) throws Throwable {
        String key = cache.key() + point.getArgs()[0];
        Object cacheRes = cacheService.get(key);
        if (cacheRes != null) return cacheRes;
        Object res = point.proceed();
        cacheService.put(key, res);
        return res;
    }

    @Around("@annotation(cache))")
    public Object clearCache(ProceedingJoinPoint point, ClearCache cache) throws Throwable {
        String key = cache.key() + point.getArgs()[0];
        cacheService.remove(key);
        return point.proceed();
    }

}
