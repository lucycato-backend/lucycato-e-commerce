package org.lucycato.userservice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppUserBadge {
    NO_BASE("뉴비", "아무것도 모르는 상태"),
    ;
    private final String display;
    private final String description;
}
