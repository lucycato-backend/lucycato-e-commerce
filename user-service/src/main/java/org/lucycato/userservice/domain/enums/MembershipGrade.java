package org.lucycato.userservice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MembershipGrade {
    ALL_PASS("강의, 교재 프리패스", "PREMIUM ALL PASS"),
    LECTURE_PASS("강의 프리패스", "COURSE PASS"),
    ;
    private final String description;
    private final String display;
}
