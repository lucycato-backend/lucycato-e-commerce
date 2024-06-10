package org.lucycato.userauthcommandservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userauthcommandservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userauthcommandservice.application.port.in.QueryAdminUserUseCase;
import org.lucycato.userauthcommandservice.application.port.in.command.*;
import org.lucycato.userauthcommandservice.application.port.out.AuthPort;
import org.lucycato.userauthcommandservice.application.port.out.QueryAdminUserPort;
import org.lucycato.userauthcommandservice.application.port.out.QueryAppUserPort;
import org.lucycato.userauthcommandservice.application.port.out.result.AdminUserResult;
import org.lucycato.userauthcommandservice.application.port.out.result.AppUserResult;
import org.lucycato.userauthcommandservice.domain.*;
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
                    deviceVo.getDeviceMacAddress(),
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
        // Boolean 만드는 것
        return queryAdminUserPort.getAdminUser(command.getName(), command.getPhoneNumber()).isPresent();
    }

    @Override
    public AdminUserProfile getAdminUserProfile(FindAdminUserIdCommand command) {
        Boolean isVerification = authPort.verifyPhoneNumberAuthCode(command.getPhoneNumberAuthCode());

        if (!isVerification) {
            throw new ApiExceptionImpl(ErrorCodeImpl.VALIDATION);
        }
        AdminUserResult adminUserResult = queryAdminUserPort.getAdminUser(command.getName(), command.getPhone())
                .orElseThrow(() -> new ApiExceptionImpl(ErrorCodeImpl.NOT_FOUND));

        return AdminUserProfile.create(
                adminUserResult.getEmail(),
                adminUserResult.getCreatedAt(),
                adminUserResult.getImageUrl()
                );
    }
}
