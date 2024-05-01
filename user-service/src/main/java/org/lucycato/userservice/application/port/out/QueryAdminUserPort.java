package org.lucycato.userservice.application.port.out;

import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.application.port.out.result.AppUserResult;

import java.util.List;

public interface QueryAdminUserPort {
    AdminUserResult getAdminUser(Long adminUserId);

    AppUserResult getAppUser(Long appUserId);

    List<AppUserResult> getAppUserList();

    List<AppUserResult> getAppUserListByUserIds(List<Long> appUserIds);

    List<DeviceVo> getAppUserDeviceInfoList(Long adminUserId);
}
