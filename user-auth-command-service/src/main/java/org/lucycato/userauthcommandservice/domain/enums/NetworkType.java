package org.lucycato.userauthcommandservice.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NetworkType {
    WIFI("wifi"),
    FIVE_G("5G"),
    FOUR_G("4G"),
    THREE_G("3G"),
    ;
    private final String description;
}
