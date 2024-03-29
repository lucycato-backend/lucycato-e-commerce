package org.lucycato.mvc.exceptionhandler;

import org.lucycato.common.api.Api;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Integer.MIN_VALUE)
@RestControllerAdvice
public class WebMvcApiExceptionHandler {

    @ExceptionHandler(value = ApiExceptionImpl.class)
    public ResponseEntity<Api<Object>> handlerApiException(ApiExceptionImpl ex) {
        // TODO: Refactoring
        ex.printStackTrace();

        return ResponseEntity
                .status(ex.getHttpCode())
                .body(Api.ERROR(ex.getResult()));
    }
}
