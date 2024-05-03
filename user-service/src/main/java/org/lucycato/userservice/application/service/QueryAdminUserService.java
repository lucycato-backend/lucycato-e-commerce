package org.lucycato.userservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.application.port.in.QueryAdminUserUseCase;
import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.application.port.out.AuthPort;
import org.lucycato.userservice.application.port.out.QueryAdminUserPort;
import org.lucycato.userservice.application.port.out.QueryAppUserPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.application.port.out.result.AppUserResult;
import org.lucycato.userservice.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class QueryAdminUserService implements QueryAdminUserUseCase {
    private final QueryAdminUserPort queryAdminUserPort;

    private final QueryAppUserPort queryAppUserPort;

    private final AuthPort authPort;

    @Override
    public AdminUser getAdminUser(GetAdminUserCommand command) {
        AdminUserResult adminUserResult = queryAdminUserPort.getAdminUser(command.getAdminUserId());
        return AdminUser.from(adminUserResult);
    }

    @Override
    public List<DeviceManagement> getAdminUserDeviceManagementList(GetAdminUserDeviceInfoCommand command) {
        List<DeviceVo> deviceVos = queryAdminUserPort.getAdminUserDeviceInfoList(command.getAdminUserId());

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

    @Override
    public AppUser getAppUser(GetAppUserByAdminUserCommand command) {
        AppUserResult appUserResult = queryAppUserPort.getAppUserResult(command.getTargetAppUserId());
        return AppUser.from(appUserResult);
    }

    @Override
    public List<AppUser> getAppUserList() {
        List<AppUserResult> appUserResults = queryAppUserPort.getAppUserList();
        return appUserResults
                .stream()
                .map(AppUser::from)
                .toList();
    }

    @Override
    public List<AppUser> getAppUserListByAppUserIds(List<Long> appUserIds) {
        List<AppUserResult> appUserResults = queryAppUserPort.getAppUserListByUserIds(appUserIds);
        return appUserResults
                .stream()
                .map(AppUser::from)
                .toList();
    }

    @Override
    public boolean certificateAdminUser(AdminUserCertificationCommand command) {
        return queryAdminUserPort.getAdminUserProfile(command.getName(), command.getPhoneNumber()).isPresent();
    }

    @Override
    public AdminUserProfile getAdminUserProfile(FindAdminUserIdCommand command) {
        Boolean isVerification = authPort.verifyPhoneNumberAuthCode(command.getPhoneNumberAuthCode());

        if (!isVerification) {
            throw new ApiExceptionImpl(ErrorCodeImpl.VALIDATION);
        }
        return queryAdminUserPort.getAdminUserProfile(command.getName(), command.getPhone())
                .orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));
    }
}
