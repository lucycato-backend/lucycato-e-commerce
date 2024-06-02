package org.lucycato.userauthcommandservice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PlatformType {
    APP("app"),
    CHROME("chrome"),
    SAFARI("safari"),
    ETC("그 외"),
    ;
    private final String description;
}
