package org.lucycato.productqueryservice.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lucycato.common.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ProductErrorCodeImpl implements ErrorCode {
    APP_USER_NOT_FOUND_BUY_COURSE(HttpStatus.NO_CONTENT.value(), "LPQ-0001", "app user not found buy course", ""),
    ADMIN_USER_NOT_TEACHER_ASSISTANCE(HttpStatus.FORBIDDEN.value(), "LPA-0002", "admin user not teacher assistance", ""),
    ;
    private final Integer httpCode;
    private final String code;
    private final String reason;
    private final String frontMessage;
}
