package org.lucycato.productservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LectureSeriesCategory {
    SERIES("시리즈 강좌"),
    NORMAL("일반 강좌")
    ;
    private final String description;
}
