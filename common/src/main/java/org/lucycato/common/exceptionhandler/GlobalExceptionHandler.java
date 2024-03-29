package org.lucycato.common.exceptionhandler;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.AsyncLoggingProducer;
import org.lucycato.common.PrintStackTraceManager;
import org.lucycato.common.api.Api;
import org.lucycato.common.error.ErrorCode;
import org.lucycato.common.error.ErrorCodeImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Order(Integer.MAX_VALUE)
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {
    private final AsyncLoggingProducer asyncLoggingProducer;
    private final PrintStackTraceManager printStackTraceManager;

    @ExceptionHandler(value = Exception.class)
    public Mono<ResponseEntity<Api<Object>>> handleGlobalException(Exception ex) {
        ex.printStackTrace();
        String stackTranceString = printStackTraceManager.getStackTraceAsString(ex);
        asyncLoggingProducer.sendLogMessage("exception", stackTranceString);

        return validateException(ex)
                .map(tuple -> ResponseEntity
                        .status(tuple.getT1().getHttpCode())
                        .body(Api.ERROR(tuple.getT1(), tuple.getT2()))
                );
    }

    private Mono<Tuple2<ErrorCode, String>> validateException(Exception ex) {
        ErrorCode errorCode;
        String reason;
        //TODO: validation exception message 고도화
        if (ex instanceof ServerWebInputException) {
            errorCode = ErrorCodeImpl.BAD_REQUEST;
            reason = errorCode.getReason() + ": " + ((ServerWebInputException) ex).getReason();
        } else if (ex instanceof MethodNotAllowedException) {
            errorCode = ErrorCodeImpl.METHOD_NOT_ALLOWED;
            reason = errorCode.getReason();
        } else if (ex instanceof WebClientRequestException) {
            errorCode = ErrorCodeImpl.REQUEST_CLIENT;
            reason = errorCode.getReason();
        } else if (ex instanceof WebClientResponseException) {
            errorCode = ErrorCodeImpl.RESPONSE_CLIENT;
            reason = errorCode.getReason();
        } else {
            errorCode = ErrorCodeImpl.INTERNAL_SERVER;
            reason = errorCode.getReason();
        }
        return Mono.just(Tuples.of(errorCode, reason));
    }
}
