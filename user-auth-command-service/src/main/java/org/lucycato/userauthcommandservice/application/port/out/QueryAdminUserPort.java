package org.lucycato.userauthcommandservice.application.port.out;

import org.lucycato.userauthcommandservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userauthcommandservice.application.port.out.result.AdminUserResult;

import java.util.List;
import java.util.Optional;

public interface QueryAdminUserPort {
    AdminUserResult getAdminUser(Long adminUserId);
    // out port의 응답값은 Result로 한다.
    Optional<AdminUserResult> getAdminUser(String name, String phoneNumber);

    List<DeviceVo> getAdminUserDeviceInfoList(Long adminUserId);
}
