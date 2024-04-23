package org.lucycato.userservice.application.port.out.persistence;

import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.model.enums.DeviceOsType;

import java.util.List;

public interface AdminUserPort {
    AdminUserResult registerAdminUser(
            String nickName,
            String name,
            String email,
            String password,
            String phoneNumber,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion
    );

    void registerDeviceInfo(
            Long adminUserid,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion
    );

    AdminUserResult getAdminUserResult(
            String email,
            String password
    );

}
