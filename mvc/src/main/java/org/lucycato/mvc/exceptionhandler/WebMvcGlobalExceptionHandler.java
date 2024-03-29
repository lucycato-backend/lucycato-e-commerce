package org.lucycato.mvc.exceptionhandler;

import org.lucycato.common.api.Api;
import org.lucycato.common.error.ErrorCodeImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(Integer.MAX_VALUE)
@RestControllerAdvice
public class WebMvcGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Api<Object>> handlerGlobalException(Exception ex) {
        // TODO: Refactoring
        ex.printStackTrace();

        return ResponseEntity
                .status(ErrorCodeImpl.INTERNAL_SERVER.getHttpCode())
                .body(Api.ERROR(ErrorCodeImpl.INTERNAL_SERVER));
    }
}
