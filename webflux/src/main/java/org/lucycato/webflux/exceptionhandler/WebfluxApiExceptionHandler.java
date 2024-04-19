package org.lucycato.webflux.exceptionhandler;

import org.lucycato.common.api.Erroresponse;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Order(Integer.MIN_VALUE)
@RestControllerAdvice
public class WebfluxApiExceptionHandler {

    @ExceptionHandler(ApiExceptionImpl.class)
    public Mono<ResponseEntity<Erroresponse<Object>>> handlerApiException(ApiExceptionImpl ex) {
        // TODO: refactoring
        ex.printStackTrace();

        return Mono.just(ResponseEntity
                .status(ex.getHttpCode())
                .body(Erroresponse.ERROR(ex.getResult()))
        );
    }
}
