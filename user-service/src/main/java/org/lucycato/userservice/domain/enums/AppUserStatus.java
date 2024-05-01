package org.lucycato.userservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppUserStatus {
    ENABLED("활성화"),
    DISABLED("비활성화"),
    ESSENTIAL_REQUIRE_ADDITIONAL("필수 추가정보 등록"),
    DORMANT("휴면 계정"),
    ;
    private final String description;
}
