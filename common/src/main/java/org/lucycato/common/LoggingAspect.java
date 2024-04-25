package org.lucycato.common;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class LoggingAspect {
    private final AsyncLoggingProducer asyncLoggingProducer;

    @Before("execution(* org.lucycato.*.adapter.in.web.*.*(..))")
    public void beforeMethodExecution(JoinPoint joinPoint) {
        // * Mono: 0 ~ 1개를 방출, Flux: 0 ~ N개 방출
        // * 즉 Mono는 1개 방출 후 dispose 된다 (방출 후 dispose 된다는 것은 메모리 누수가 발생을 안한다.)
        String methodName = joinPoint.getSignature().getName();
        asyncLoggingProducer.sendLogMessage("logging", "Before executing method: %s".formatted(methodName));
    }
}
