package org.lucycato.common.security;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppUserRole {
    NORMAL("일반")
    ;
    private final String description;
}
