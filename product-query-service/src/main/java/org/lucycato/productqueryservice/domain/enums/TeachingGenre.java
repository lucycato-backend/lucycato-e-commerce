package org.lucycato.productqueryservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TeachingGenre {
    DEVELOPER("개발"),
    DESIGN("디자인"),
    BUSINESS("비지니스"),
    ;

    private final String description;
}
