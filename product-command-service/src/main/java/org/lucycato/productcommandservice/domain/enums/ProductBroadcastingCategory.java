package org.lucycato.productcommandservice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ProductBroadcastingCategory {
    COURSE_UPLOAD("신규 강의 업로드")
    ;
    private final String description;
}
