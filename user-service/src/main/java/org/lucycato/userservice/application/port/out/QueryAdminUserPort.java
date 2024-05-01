package org.lucycato.userservice.application.port.out;

import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.application.port.out.result.AppUserResult;

import java.util.List;

public interface QueryAdminUserPort {
    AdminUserResult getAdminUser(Long adminUserId);

    List<DeviceVo> getAdminUserDeviceInfoList(Long adminUserId);
}
