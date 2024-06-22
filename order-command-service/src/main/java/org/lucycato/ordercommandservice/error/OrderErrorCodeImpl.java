package org.lucycato.ordercommandservice.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lucycato.common.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum OrderErrorCodeImpl implements ErrorCode {
    CANNOT_CHANGE_COUPON_TYPE(HttpStatus.BAD_REQUEST.value(), "LO-0001", "cannot change coupon type", "")
    ;
    private final Integer httpCode;
    private final String code;
    private final String reason;
    private final String frontMessage;
}
