package org.lucycato.common.exceptionhandler;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.AsyncLoggingProducer;
import org.lucycato.common.PrintStackTraceManager;
import org.lucycato.common.api.Api;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import reactor.core.publisher.Mono;

@Order(Integer.MIN_VALUE)
@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler {
    private final AsyncLoggingProducer asyncLoggingProducer;
    private final PrintStackTraceManager printStackTraceManager;

    @ExceptionHandler(value = ApiExceptionImpl.class)
    public Mono<ResponseEntity<Api<Object>>> handleApiException(ApiExceptionImpl ex) {
        ex.printStackTrace();
        String stackTraceString = printStackTraceManager.getStackTraceAsString(ex);
        asyncLoggingProducer.sendLogMessage("exception", stackTraceString);

        return Mono.just(ResponseEntity
                .status(ex.getHttpCode())
                .body(Api.ERROR(ex.getResult()))
        );
    }
}
