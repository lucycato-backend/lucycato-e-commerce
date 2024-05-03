package org.lucycato.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum UserErrorCodeImpl implements ErrorCode {

    USER_NOT_MATH(HttpStatus.NOT_FOUND.value(), "LU-001", "not found user", "아이디 또는 비빌번호를 다시 확인해주세요."),
    PHONE_NUMBER_VERIFICATION_NOT_MATCH(HttpStatus.NOT_FOUND.value(), "LU-002", "phone number verification not match", "전화번호 인증이 유요하지 않습니다."),
    USER_DUPLICATE(HttpStatus.NOT_FOUND.value(), "LU-002", "user duplicate", "중복된 아이디가 있습니다."),
    EXIST_USER(HttpStatus.BAD_REQUEST.value(), "LU-003", "exist user","기존 회원이 존재합니다."),
    EMAIL_NOT_MATH(HttpStatus.BAD_REQUEST.value(), "LU-004", "exist user","가입된 이메일이 없습니다."),
    ;
    private final Integer httpCode;
    private final String code;
    private final String reason;
    private final String frontMessage;
}
