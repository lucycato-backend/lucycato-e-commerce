package org.lucycato.usercoursequeryservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TeacherImageCategory {
    MAIN("main image"),
    SUB("sub image"),
    FULL("full image"),
    ;
    private final String description;
}
