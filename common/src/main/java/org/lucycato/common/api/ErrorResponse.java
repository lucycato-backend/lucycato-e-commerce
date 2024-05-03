package org.lucycato.common.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.error.ErrorCode;

@Getter
@NoArgsConstructor
public class ErrorResponse<T> {
    private Result result;
    private T body;

    public static ErrorResponse<Object> ERROR(Result result) {
        ErrorResponse<Object> ErrorResponse = new ErrorResponse<>();
        ErrorResponse.result = result;
        ErrorResponse.body = new Object();
        return ErrorResponse;
    }

    public static ErrorResponse<Object> ERROR(ErrorCode errorCode) {
        ErrorResponse<Object> ErrorResponse = new ErrorResponse<>();
        ErrorResponse.result = Result.ERROR(errorCode);
        ErrorResponse.body = new Object();
        return ErrorResponse;
    }

    public static ErrorResponse<Object> ERROR(ErrorCode errorCode, String reason) {
        ErrorResponse<Object> ErrorResponse = new ErrorResponse<>();
        ErrorResponse.result = Result.ERROR(errorCode, reason);
        ErrorResponse.body = new Object();
        return ErrorResponse;
    }
}
