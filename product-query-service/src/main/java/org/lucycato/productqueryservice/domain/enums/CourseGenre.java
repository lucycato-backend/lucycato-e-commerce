package org.lucycato.productqueryservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CourseGenre {
    FOUNDATION("기초"),
    PRACTICAL("실전"),
    ;
    private final String description;
}
