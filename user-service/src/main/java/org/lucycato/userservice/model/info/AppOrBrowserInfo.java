package org.lucycato.userservice.model.info;

import lombok.*;
import org.lucycato.userservice.model.enums.AppOrBrowserType;
import org.lucycato.userservice.model.enums.NetworkType;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppOrBrowserInfo {
    private AppOrBrowserType appOrBrowserType;

    private String appOrBrowserVersion;

    private NetworkType networkType;

    private String locale;

    private LocalDateTime lastLoginAt;

    private LocalDateTime lastLogoutAt;
}
