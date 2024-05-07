package org.lucycato.productservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LectureProductWay {
    ONLINE("스튜디오 강의"),
    OFFLINE("현장 강의"),
    ;
    private final String description;
}
