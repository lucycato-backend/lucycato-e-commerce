package org.lucycato.userauthcommandservice.application.port.out;

import org.lucycato.userauthcommandservice.application.port.out.result.AppUserResult;
import org.lucycato.userauthcommandservice.adapter.out.persistence.vo.DeviceVo;

import java.util.List;

public interface QueryAppUserPort {

    AppUserResult getAppUserResultByEmail(String email);

    AppUserResult getAppUserResultByPhoneNumber(String phoneNumber);

    AppUserResult getAppUserResult(Long appUserId);

    List<AppUserResult> getAppUserList();

    List<AppUserResult> getAppUserListByUserIds(List<Long> appUserIds);

    List<DeviceVo> getAppUserDeviceInfoList(Long appUserId);
}
