package org.lucycato.gatewayserver.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.lucycato.common.error.ErrorCode;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum GatewayErrorCodeImpl implements ErrorCode {
    INVALID_URI(HttpStatus.BAD_REQUEST.value(), "LG-0001", "invalid uri", ""),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED.value(), "LG-0002", "invalid token", "");
    ;
    private final Integer httpCode;
    private final String code;
    private final String reason;
    private final String frontMessage;
}
