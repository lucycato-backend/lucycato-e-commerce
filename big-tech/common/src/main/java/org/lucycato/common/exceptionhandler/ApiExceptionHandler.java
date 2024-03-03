package org.lucycato.common.exceptionhandler;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.LoggingProducer;
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
    private final LoggingProducer loggingProducer;
    private final PrintStackTraceManager printStackTraceManager;

    @ExceptionHandler(value = ApiExceptionImpl.class)
    public Mono<ResponseEntity<Api<Object>>> handleApiException(ApiExceptionImpl ex) {
        ex.printStackTrace();
        String stackTraceString = printStackTraceManager.getStackTraceAsString(ex);
        loggingProducer.sendLogMessage("exception", stackTraceString).subscribe();

        return Mono.just(ResponseEntity
                .status(ex.getErrorCode().getHttpCode())
                .body(Api.ERROR(ex.getErrorCode(), ex.getReason()))
        );
    }
}
