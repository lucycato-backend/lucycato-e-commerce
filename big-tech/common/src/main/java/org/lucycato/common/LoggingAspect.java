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
        // * Mono: 0 ~ 1개를 방출, Flux: 0 ~ N개 방출
        // * 즉 Mono는 1개 방출 후 dispose 된다 (방출 후 dispose 된다는 것은 메모리 누수가 발생을 안한다.)
        Mono.just(joinPoint.getSignature().getName())
                .flatMap(methodName -> loggingProducer.sendLogMessage("logging", "Before executing method: %s".formatted(methodName)))
                .subscribe();
    }
}
