package org.lucycato.userservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.userservice.application.port.in.QueryAppUserUseCase;
import org.lucycato.userservice.application.port.in.command.GetAppUserCommand;
import org.lucycato.userservice.application.port.in.command.GetAppUserDeviceManagementCommand;
import org.lucycato.userservice.application.port.out.QueryAppUserPort;
import org.lucycato.userservice.application.port.out.result.AppUserResult;
import org.lucycato.userservice.domain.AppUser;
import org.lucycato.userservice.domain.DeviceManagement;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.domain.Platform;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QueryAppUserService implements QueryAppUserUseCase {
    private final QueryAppUserPort queryAppUserPort;

    @Override
    public AppUser getAppUser(GetAppUserCommand command) {
        AppUserResult appUserResult = queryAppUserPort.getAppUserResult(command.getAppUserId());
        return AppUser.from(appUserResult);
    }

    @Override
    public List<DeviceManagement> getAppUserDeviceManagementList(GetAppUserDeviceManagementCommand command) {
        List<DeviceVo> deviceVos = queryAppUserPort.getAppUserDeviceInfoList(command.getAppUserId());
        return deviceVos.stream().map(deviceVo -> {
            List<Platform> platforms = deviceVo.getPlatformVos().stream().map(platformVo -> Platform.create(
                    platformVo.getPlatformType(),
                    platformVo.getPlatformVersion(),
                    platformVo.getNetworkType(),
                    platformVo.getPlatformVersion(),
                    platformVo.getLastLoginAt(),
                    platformVo.getLastLogoutAt()
            )).toList();
            return DeviceManagement.create(
                    deviceVo.getDeviceManAddress(),
                    deviceVo.getDeviceFcmToken(),
                    deviceVo.getDeviceOsType(),
                    deviceVo.getDeviceOsVersion(),
                    platforms
            );
        }).toList();
    }
}
