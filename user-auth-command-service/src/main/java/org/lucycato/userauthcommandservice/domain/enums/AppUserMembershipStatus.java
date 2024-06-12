package org.lucycato.userauthcommandservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppUserMembershipStatus {
    OPERATOR("사용중"),
    REMOVED("삭제"),
    ;
    private final String description;
}
