package org.lucycato.productcommandservice.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lucycato.common.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductCommandErrorCodeImpl implements ErrorCode {
    ADMIN_USER_NOT_CHANGE_TO_TEACHER(HttpStatus.FORBIDDEN.value(), "LPA-0002", "admin user not teacher assistance", ""),
    ;

    private final Integer httpCode;
    private final String code;
    private final String reason;
    private final String frontMessage;
}
