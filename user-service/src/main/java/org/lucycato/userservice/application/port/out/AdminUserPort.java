package org.lucycato.userservice.application.port.out;

import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.model.enums.AppOrBrowserType;
import org.lucycato.userservice.model.enums.DeviceOsType;
import org.lucycato.userservice.model.enums.NetworkType;

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
            String deviceOsVersion,
            AppOrBrowserType appOrBrowserType,
            String appOrBrowserVersion,
            NetworkType networkType,
            String locale
    );

    void modifyDeviceInfo(
            Long adminUserid,
            String deviceMacAddress,
            String deviceFcmToken,
            DeviceOsType deviceOsType,
            String deviceOsVersion,
            AppOrBrowserType appOrBrowserType,
            String appOrBrowserVersion,
            NetworkType networkType,
            String locale
    );

    void expireAdminUser(Long adminUserId);

    AdminUserResult getAdminUserResult(
            String email,
            String password
    );

    AdminUserResult addAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole);

    AdminUserResult removeAdminUserRole(Long adminUserId, AdminUserRole targetAdminUserRole);
}
