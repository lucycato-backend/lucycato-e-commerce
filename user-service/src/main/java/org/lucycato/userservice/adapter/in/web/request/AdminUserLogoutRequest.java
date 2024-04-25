package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.model.enums.AppOrBrowserType;

@Getter
@NoArgsConstructor
public class AdminUserLogoutRequest {
    private String deviceMacAddress;

    private AppOrBrowserType appOrBrowserType;
}
