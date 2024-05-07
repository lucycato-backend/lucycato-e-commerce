package org.lucycato.productservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LectureContentCategory {
    OT("OT"),
    PREVIEW("맛보기"),
    APPROVAL("승인 필요"),
    ;
    private final String description;
}
