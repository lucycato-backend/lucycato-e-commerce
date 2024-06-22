package org.lucycato.productcommandservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LectureStatus {
    REGISTERED("등록"),
    UNREGISTERED("등록 해지"),
    ;
    private final String description;
}
