package org.lucycato.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCodeImpl implements ErrorCode {
    OK(HttpStatus.OK.value(), "0000", "Success"),
    ;
    private final Integer httpCode;
    private final String code;
    private final String reason;
}
