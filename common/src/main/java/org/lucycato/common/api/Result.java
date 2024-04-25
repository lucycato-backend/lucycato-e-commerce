package org.lucycato.common.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.error.ErrorCode;

@Getter
@NoArgsConstructor
public class Result {
    private String code;
    private String reason;
    private String frontMessage;

    public static Result ERROR(ErrorCode errorCode) {
        Result result = new Result();
        result.code = errorCode.getCode();
        result.reason = errorCode.getReason();
        result.frontMessage = errorCode.getFrontMessage();
        return result;
    }

    public static Result ERROR(ErrorCode errorCode, String reason) {
        Result result = new Result();
        result.code = errorCode.getCode();
        result.reason = reason;
        result.frontMessage = errorCode.getFrontMessage();
        return result;
    }
}


