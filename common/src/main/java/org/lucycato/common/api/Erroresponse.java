package org.lucycato.common.api;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.error.ErrorCode;

@Getter
@NoArgsConstructor
public class Erroresponse<T> {
    private Result result;
    private T body;

    public static Erroresponse<Object> ERROR(Result result) {
        Erroresponse<Object> Erroresponse = new Erroresponse<>();
        Erroresponse.result = result;
        Erroresponse.body = new Object();
        return Erroresponse;
    }

    public static Erroresponse<Object> ERROR(ErrorCode errorCode) {
        Erroresponse<Object> Erroresponse = new Erroresponse<>();
        Erroresponse.result = Result.ERROR(errorCode);
        Erroresponse.body = new Object();
        return Erroresponse;
    }

    public static Erroresponse<Object> ERROR(ErrorCode errorCode, String reason) {
        Erroresponse<Object> Erroresponse = new Erroresponse<>();
        Erroresponse.result = Result.ERROR(errorCode, reason);
        Erroresponse.body = new Object();
        return Erroresponse;
    }
}
