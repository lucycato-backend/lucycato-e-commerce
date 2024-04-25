package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.model.enums.AppOrBrowserType;
import org.lucycato.userservice.model.enums.NetworkType;

@Getter
@NoArgsConstructor
public class AppOrDeviceInfoRequest {
    private AppOrBrowserType appOrBrowserType;

    private String appOrBrowserVersion;

    private NetworkType networkType;

    private String locale;
}
