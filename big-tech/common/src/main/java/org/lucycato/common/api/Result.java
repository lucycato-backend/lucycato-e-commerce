package org.lucycato.common.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.error.ErrorCode;
import org.lucycato.common.error.ErrorCodeImpl;

@Getter
@NoArgsConstructor
public class Result {
    private String code;
    private String reason;

    public static Result OK() {
        Result result = new Result();
        result.code = ErrorCodeImpl.OK.getCode();
        result.reason = ErrorCodeImpl.OK.getReason();
        return result;
    }

    public static Result ERROR(ErrorCode errorCode) {
        Result result = new Result();
        result.code = errorCode.getCode();
        result.reason = errorCode.getReason();
        return result;
    }

    public static Result ERROR(ErrorCode errorCode, String reason) {
        Result result = new Result();
        result.code = errorCode.getCode();
        result.reason = reason;
        return result;
    }

    public static Result ERROR(ErrorCode errorCode, Throwable tx) {
        Result result = new Result();
        result.code = errorCode.getCode();
        result.reason = tx.getLocalizedMessage();
        return result;
    }
}


