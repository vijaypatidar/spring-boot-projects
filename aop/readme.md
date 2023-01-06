# Spring Boot AOP
The AOP (Aspect Oriented Programming) part of Spring supports unified development by ensuring separation of application’s business logic from other system services.

1. Add spring AOP dependency

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

2. Create java annotation with Runtime retention

```java

@Retention(RetentionPolicy.RUNTIME)
public @interface Loggable {
    String description();
}
```

3. Create Advice class annotated with @Aspect

```java

@Component
@Aspect
public class LoggerAspect {

}
```

4.Create method annotated with @Around("@annotation()")

```java

@Component
@Aspect
public class LoggerAspect {

    private final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

    @Around("@annotation(log))")
    public Object run(ProceedingJoinPoint point, Loggable log) {
        try {
            //save log some where in persist db
            logger.info(
                    point.getSignature().getName() + " called with args " + Arrays.stream(point.getArgs()).collect(Collectors.toList()) + ", Description = " + log.description()
            );
            //proceed methods execution
            Object res = point.proceed();
            logger.debug(
                    point.getSignature().getName() + " executed successfully : " + log.description()
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
```

5. Use Loggable annotation to our code

```java

@RequestMapping("/users")
@RestController
public class UserController {
    //...

    @Loggable(description = "New user creation request")
// pass description which can be access by our LoggerAspect class
    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userService.addUser(user);
        return ResponseEntity.ok("User add " + user.getName());
    }

    //...
}
```

## Things we can do with AOP
1. Retry mechanism : Faults are common in software, but it's our responsibility to deal with them to make our application fault tolerance. 
2. Caching: It's common and good practice to store results of expensive operation that is required frequently. Using AOP we can create caching tool with annotation, this will help us to remove the duplicate logic by just annotating the method.
3. Metric collection: We want our application to be reliable. It should serve the request in minimum time(latency should be small) and for this we need to keep eye on our metric. Using AOP we can easily record the time taken by methods without even changing their definition and respecting the SRP of each method. 
