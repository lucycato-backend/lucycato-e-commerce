package org.lucycato.userservice.application.port.out;

import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.model.enums.DeviceOsType;

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

    void modifyDeviceInfo(
            Long adminUserid,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion
    );

    void expireAdminUser(Long adminUserId);

    AdminUserResult getAdminUserResult(
            String email,
            String password
    );

    AdminUserResult addAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole);

    AdminUserResult removeAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole);
}
