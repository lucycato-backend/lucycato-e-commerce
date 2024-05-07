package org.lucycato.productservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ReviewCategory {
    BEST("베스트 수강평"),
    NORMAL("일반 수강평"),
    ;
    private final String description;
}
