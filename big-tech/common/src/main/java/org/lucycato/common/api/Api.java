package org.lucycato.common.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.error.ErrorCode;

@Getter
@NoArgsConstructor
public class Api<T> {
    private Result result;
    private T body;

    public static <T> Api<T> OK(T body) {
        Api<T> api = new Api<>();
        api.result = Result.OK();
        api.body = body;
        return api;
    }

    public static Api<Object> ERROR(Result result) {
        Api<Object> api = new Api<>();
        api.result = result;
        api.body = new Object();
        return api;
    }

    public static Api<Object> ERROR(ErrorCode errorCode) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCode);
        api.body = new Object();
        return api;
    }

    public static Api<Object> ERROR(ErrorCode errorCode, String reason) {
        Api<Object> api = new Api<>();
        api.result = Result.ERROR(errorCode, reason);
        api.body = new Object();
        return api;
    }
}
