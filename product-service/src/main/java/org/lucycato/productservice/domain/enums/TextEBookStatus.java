package org.lucycato.productservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TextEBookStatus {
    OPERATOR("운용"),
    NOT_OPERATOR("비운용"),
    ;
    private final String description;
}
