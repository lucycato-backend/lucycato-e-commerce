package org.lucycato.webflux.exceptionhandler;

import org.lucycato.common.api.Erroresponse;
import org.lucycato.common.error.ErrorCodeImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

@Order(Integer.MAX_VALUE)
@RestControllerAdvice
public class WebfluxGlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Erroresponse<Object>>> handlerGlobalException(Exception ex) {
        // TODO: refactoring
        ex.printStackTrace();

        return Mono.just(ResponseEntity
                .status(ErrorCodeImpl.INTERNAL_SERVER.getHttpCode())
                .body(Erroresponse.ERROR(ErrorCodeImpl.INTERNAL_SERVER))
        );
    }
}
