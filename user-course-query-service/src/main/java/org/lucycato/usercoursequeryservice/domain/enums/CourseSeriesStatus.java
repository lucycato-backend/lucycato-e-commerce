package org.lucycato.usercoursequeryservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CourseSeriesStatus {
    REGISTERED("등록"),
    UNREGISTERED("등록 해지"),
    ;
    private final String description;
}
