package org.lucycato.productservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum LectureGenre {
    FOUNDATION("기초"),
    PRACTICAL("실전"),
    ;
    private final String description;
}
