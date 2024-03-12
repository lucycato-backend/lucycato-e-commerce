package org.lucycato.common.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.error.ErrorCode;

@Getter
@NoArgsConstructor
public class Result {
    private static final String SUCCESS_CODE = "LUCYCATO";
    private static final String SUCCESS_REASON = "success";
    private String code;
    private String reason;
    private String frontMessage;

    public static Result OK() {
        Result result = new Result();
        result.code = Result.SUCCESS_CODE;
        result.reason = Result.SUCCESS_REASON;
        result.frontMessage = "";
        return result;
    }

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


