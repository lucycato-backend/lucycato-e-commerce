package org.lucycato.productqueryservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CourseSeriesCategory {
    SERIES("시리즈 강좌"),
    NORMAL("일반 강좌")
    ;
    private final String description;
}
