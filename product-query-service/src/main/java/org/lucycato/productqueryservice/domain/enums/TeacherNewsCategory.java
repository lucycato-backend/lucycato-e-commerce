package org.lucycato.productqueryservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TeacherNewsCategory {
    NOTICE("공지사항"),
    EVENT("이벤트"),
    OPEN("개강"),
    ;
    private final String description;
}
