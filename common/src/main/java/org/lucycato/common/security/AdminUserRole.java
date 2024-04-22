package org.lucycato.common.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AdminUserRole {
    MASTER("마스터 권한"),
    VIEWER("보기 권한"),
    ;
    private final String display;
}
