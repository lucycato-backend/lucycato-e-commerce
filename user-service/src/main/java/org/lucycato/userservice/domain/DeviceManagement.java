package org.lucycato.userservice.domain;


import lombok.*;
import org.lucycato.userservice.model.enums.UserStatus;
import org.lucycato.userservice.model.info.DeviceInfo;

import java.util.List;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class DeviceManagement {
    private Long userId;

    private UserStatus userStatus;

    private List<DeviceInfo> deviceInfos;

    public static DeviceManagement create(
            Long userId,
            UserStatus userStatus,
            List<DeviceInfo> deviceInfos
    ) {
        return DeviceManagement.builder()
                .userId(userId)
                .userStatus(userStatus)
                .deviceInfos(deviceInfos)
                .build();
    }
}
