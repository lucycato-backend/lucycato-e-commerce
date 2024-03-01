package org.lucycato.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lucycato.common.error.ErrorCode;

@Getter
@AllArgsConstructor
public class ApiExceptionImpl extends RuntimeException implements ApiException {
    private ErrorCode errorCode;
    private String reason;
}
