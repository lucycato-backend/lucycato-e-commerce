package org.lucycato.common;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final LoggingProducer loggingProducer;

    @Before("execution(* com.lucycato.*.adapter.in.web.*.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        Mono.just(joinPoint.getSignature().getName())
                .flatMap(methodName -> loggingProducer.sendLogMessage("logging", "Before executing method: %s".formatted(methodName)))
                .subscribe();
    }
}
