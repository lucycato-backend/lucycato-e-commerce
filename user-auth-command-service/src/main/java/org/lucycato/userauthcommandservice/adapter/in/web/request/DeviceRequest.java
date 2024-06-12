package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userauthcommandservice.domain.enums.DeviceOsType;

@Getter
@NoArgsConstructor
public class DeviceRequest {
    private String deviceMacAddress;

    private String deviceFcmToken;

    private DeviceOsType deviceOsType;

    private String deiceOsVersion;
}
