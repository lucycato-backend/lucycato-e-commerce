package org.lucycato.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.api.Result;
import org.lucycato.common.error.ErrorCode;

@Getter
@NoArgsConstructor
public class ApiExceptionImpl extends RuntimeException implements ApiException {
    private Integer httpCode;
    private Result result;

    public ApiExceptionImpl(Integer httpCode, Result result) {
        super(result.getReason());
        this.httpCode = httpCode;
        this.result = result;
    }

    public ApiExceptionImpl(ErrorCode errorCode) {
        super(errorCode.getReason());
        this.httpCode = errorCode.getHttpCode();
        this.result = Result.ERROR(errorCode);
    }

    public ApiExceptionImpl(ErrorCode errorCode, String reason) {
        super(reason);
        this.httpCode = errorCode.getHttpCode();
        this.result = Result.ERROR(errorCode, reason);
    }

    public ApiExceptionImpl(ErrorCode errorCode, Throwable tx) {
        super(tx.getLocalizedMessage());
        this.httpCode = errorCode.getHttpCode();
        this.result = Result.ERROR(errorCode);
    }
}