package org.lucycato.mvc.exceptionhandler;

import jakarta.validation.UnexpectedTypeException;
import lombok.extern.slf4j.Slf4j;
import org.lucycato.common.api.ErrorResponse;
import org.lucycato.common.error.ErrorCode;
import org.lucycato.common.error.ErrorCodeImpl;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.rmi.UnexpectedException;

@Order(Integer.MAX_VALUE)
@RestControllerAdvice
@Slf4j
public class WebMvcGlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse<Object>> handlerGlobalException(Exception ex) {


        if (ex instanceof UnexpectedTypeException) {
            log.error("command Validation: {}", ex);
            return ResponseEntity
                    .status(ErrorCodeImpl.VALIDATION.getHttpCode())
                    .body(ErrorResponse.ERROR(ErrorCodeImpl.VALIDATION));

        }
        // TODO: Refactoring
        ex.printStackTrace();

        return ResponseEntity
                .status(ErrorCodeImpl.INTERNAL_SERVER.getHttpCode())
                .body(ErrorResponse.ERROR(ErrorCodeImpl.INTERNAL_SERVER));
    }
}
