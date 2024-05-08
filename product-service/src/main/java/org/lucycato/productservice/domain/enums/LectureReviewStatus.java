package org.lucycato.productservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LectureReviewStatus {
    REGISTERED("등록"),
    UNREGISTERED("등록 해지"),

    ;
    private final String description;
}
