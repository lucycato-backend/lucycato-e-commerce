package org.lucycato.userauthcommandservice.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum DeviceOsType {
    MAC("mac"),
    WINDOW("window"),
    IOS("iOS"),
    ANDROID("Android"),
    ;
    private final String description;
}
