package org.lucycato.userservice.application.port.out;

import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.domain.AdminUserProfile;

import java.util.List;
import java.util.Optional;

public interface QueryAdminUserPort {
    AdminUserResult getAdminUser(Long adminUserId);

    Optional<AdminUserProfile> getAdminUserProfile(String name, String phoneNumber);

    List<DeviceVo> getAdminUserDeviceInfoList(Long adminUserId);
}
