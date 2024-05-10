package org.lucycato.userservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum AppUserCareer {

    STUDENT("학생", "학생 신분"),
    JOBSEEKER("구직중","구직자 신분"),
    EMPLOYEE("직장인", "직장인 신분")
    ;

    private final String display;
    private final String description;
}
