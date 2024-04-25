package org.lucycato.userservice.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AppOrBrowserType {
    APP("app"),
    CHROME("chrome"),
    SAFARI("safari"),
    ETC("그 외"),
    ;
    private final String description;
}
