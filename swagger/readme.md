# Spring Boot AOP

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

    @Around("@annotation(loggable))")
    public Object run(ProceedingJoinPoint point, Loggable loggable) {
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