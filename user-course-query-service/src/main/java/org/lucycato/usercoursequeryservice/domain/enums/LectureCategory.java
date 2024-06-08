package org.lucycato.usercoursequeryservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LectureCategory {
    OT("OT"),
    PREVIEW("맛보기"),
    APPROVAL("승인 필요"),
    ;
    private final String description;
}
