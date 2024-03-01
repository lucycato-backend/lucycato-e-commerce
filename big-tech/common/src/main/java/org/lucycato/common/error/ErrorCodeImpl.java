package org.lucycato.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeImpl implements ErrorCode {
    INTERNAL_SERVER(HttpStatus.INTERNAL_SERVER_ERROR.value(), "LC-0001", "internal server error", ""),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "LC-0002", "bad request", ""),
    CLIENT(413, "LC-0003", "request server error reason", ""),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), "LC-0004", "null point", ""),
    VALIDATION(HttpStatus.INTERNAL_SERVER_ERROR.value(), "LC-0005", "fail validation", ""),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED.value(), "LC-0006", "method not allowed please method change", "")
    ;
    private final Integer httpCode;
    private final String code;
    private final String reason;
    private final String frontMessage;

    @Override
    public RuntimeException build(Object ...args) {
        return new ApiExceptionImpl(this, reason.formatted(args));
    }
}
