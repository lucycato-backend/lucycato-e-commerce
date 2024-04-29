package org.lucycato.userservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserStatus {
    APP("app user"),
    ADMIN("admin user"),
    ;
    private final String description;
}
