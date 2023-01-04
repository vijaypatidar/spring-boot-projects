package com.example.aop.apsect.retryable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Retry {
    Class<? extends Exception> exception();
    int retryCount();
}
