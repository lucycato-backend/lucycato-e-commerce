package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;
import org.lucycato.userservice.model.enums.AppOrBrowserType;
import org.lucycato.userservice.model.enums.DeviceOsType;
import org.lucycato.userservice.model.enums.NetworkType;

import java.util.List;

@Getter
@NoArgsConstructor
public class DeviceInfoRequest {
    private String deviceMacAddress;

    private String deviceFcmToken;

    private DeviceOsType deviceOsType;

    private String deiceOsVersion;
}
