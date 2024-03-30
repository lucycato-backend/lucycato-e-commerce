package org.lucycato.userservice.model.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppVersionCheckStatus {
    PASS("앱 업데이트 필요 없음", "통과"),
    REQUIRE("앱 업데이트 권유", "권유"),
    FORCE("강제 업데이트", "강제"),
    ;
    private final String description;
    private final String message;
}
