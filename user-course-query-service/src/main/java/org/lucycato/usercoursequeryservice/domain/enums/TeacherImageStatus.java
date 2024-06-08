package org.lucycato.usercoursequeryservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TeacherImageStatus {
    MAIN("main"),
    DETAIL("detail"),
    SUB("sub"),
    ;
    private final String description;
}
