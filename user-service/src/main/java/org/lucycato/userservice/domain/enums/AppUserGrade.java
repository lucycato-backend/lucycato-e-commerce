package org.lucycato.userservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppUserGrade {
    BRONZE("브론즈", "브론즈 등급"),
    SILVER("실버", "실버 등급"),
    GOLD("골드", "골드 등급"),
    PLATINUM("플래티넘", "플래티넘 등급"),
    DIAMOND("다이아몬드","다이아몬드 등급"),
    ;
    private final String display;
    private final String description;
}
