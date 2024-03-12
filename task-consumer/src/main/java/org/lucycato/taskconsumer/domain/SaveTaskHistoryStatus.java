package org.lucycato.taskconsumer.domain;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum SaveTaskHistoryStatus {
    REQUEST("요청"),
    SUCCESS("성공"),
    FAIL("실패"),
    ;
    private final String description;
}
