package org.lucycato.userservice.application.service;

import io.netty.util.internal.StringUtil;
import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.error.UserErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.application.port.in.AdminUserUseCase;
import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.application.port.out.AdminUserPort;
import org.lucycato.userservice.application.port.out.AuthPort;
import org.lucycato.userservice.application.port.out.DeviceManagementPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.application.port.out.result.AdminfindPasswordResult;
import org.lucycato.userservice.application.port.out.result.IssueJwtTokenResult;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AdminUserLogin;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.Random;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminUserService implements AdminUserUseCase {

    private final AdminUserPort adminUserPort;

    private final DeviceManagementPort deviceManagementPort;

    private final AuthPort authPort;

    @Override
    public AdminUserLogin register(AdminUserRegisterCommand command) {
        Boolean isVerification = authPort.verifyPhoneNumberAuthCode(command.getPhoneNumberAuthCode());

        if (isVerification) {
            AdminUserResult adminUserResult = adminUserPort.registerAdminUser(
                    command.getNickName(),
                    command.getName(),
                    command.getEmail(),
                    command.getPassword(),
                    command.getPhoneNumber()
            );

            deviceManagementPort.modifyAdminUserDeviceInfo(
                    adminUserResult.getAdminUserId(),
                    command.getCurrentAdminUserDeviceMacAddress(),
                    command.getCurrentAdminUserDeviceFcmToken(),
                    command.getCurrentAdminUserDeviceOsType(),
                    command.getCurrentAdminUserDeiceOsVersion(),
                    command.getCurrentAdminUserPlatformType(),
                    command.getCurrentAdminUserPlatformVersion(),
                    command.getCurrentAdminUserNetworkType(),
                    command.getCurrentAdminUserLocale()
            );

            IssueJwtTokenResult issueJwtTokenResult = authPort.issueAdminUserJwtToken(
                    adminUserResult.getAdminUserId(),
                    adminUserResult.getAdminUserRoles(),
                    command.getCurrentAdminUserDeviceMacAddress(),
                    command.getCurrentAdminUserPlatformType()
            );

            return AdminUserLogin.from(adminUserResult, issueJwtTokenResult);
        } else {
            throw new ApiExceptionImpl(UserErrorCodeImpl.PHONE_NUMBER_VERIFICATION_NOT_MATCH);
        }
    }

    @Override
    public AdminUserLogin login(AdminUserLoginCommand command) {
        AdminUserResult adminUserResult;
        try {
            adminUserResult = adminUserPort.getAdminUserResult(command.getEmail(), command.getPassword());
        } catch (ApiExceptionImpl e) {
            throw new ApiExceptionImpl(UserErrorCodeImpl.USER_NOT_MATH);
        }

        deviceManagementPort.modifyAdminUserDeviceInfo(
                adminUserResult.getAdminUserId(),
                command.getCurrentAdminUserDeviceMacAddress(),
                command.getCurrentAdminUserDeviceFcmToken(),
                command.getCurrentAdminUserDeviceOsType(),
                command.getCurrentAdminUserDeiceOsVersion(),
                command.getCurrentAdminUserPlatformType(),
                command.getCurrentAdminUserPlatformVersion(),
                command.getCurrentAdminUserNetworkType(),
                command.getCurrentAdminUserLocale()
        );

        IssueJwtTokenResult issueJwtTokenResult = authPort.issueAdminUserJwtToken(
                adminUserResult.getAdminUserId(),
                adminUserResult.getAdminUserRoles(),
                command.getCurrentAdminUserDeviceMacAddress(),
                command.getCurrentAdminUserPlatformType()
        );

        return AdminUserLogin.from(adminUserResult, issueJwtTokenResult);
    }

    @Override
    public void loginCheck(AdminUserLoginCheckCommand command) {
        deviceManagementPort.modifyAdminUserDeviceInfo(
                command.getAdminUserId(),
                command.getCurrentAdminUserDeviceMacAddress(),
                command.getCurrentAdminUserDeviceFcmToken(),
                command.getCurrentAdminUserDeviceOsType(),
                command.getCurrentAdminUserDeiceOsVersion(),
                command.getCurrentAdminUserPlatformType(),
                command.getCurrentAdminUserPlatformVersion(),
                command.getCurrentAdminUserNetworkType(),
                command.getCurrentAdminUserLocale()
        );
    }

    @Override
    public void logout(AdminUserLogoutCommand command) {

        deviceManagementPort.modifyAdminUserDeviceLogout(
                command.getAdminUserId(),
                command.getCurrentAdminUserDeviceMacAddress(),
                command.getCurrentAdminUserPlatformType()
        );
    }

    @Override
    public AdminUser addAdminUserRole(ModifyAdminUserRoleCommand command) {
        AdminUserResult adminUserResult = adminUserPort.addAdminUserRole(
                command.getTargetAdminUserId(),
                command.getTargetChangeRole()
        );

        return AdminUser.from(adminUserResult);
    }

    @Override
    public AdminUser removeAdminUserRole(ModifyAdminUserRoleCommand command) {
        AdminUserResult adminUserResult = adminUserPort.removeAdminUserRole(
                command.getTargetAdminUserId(),
                command.getTargetChangeRole()
        );
        return AdminUser.from(adminUserResult);
    }

    @Override
    public boolean createAdminUserTempPassword(AdminUserCreateTempPasswordCommand command) {
        AdminUserResult adminUserResult;
        try {
            adminUserResult = adminUserPort.getAdminUserResult(command.getEmail());
        } catch (ApiExceptionImpl e) {
            throw new ApiExceptionImpl(UserErrorCodeImpl.EMAIL_NOT_MATH);
        }

        try {
            if (!ObjectUtils.isEmpty(adminUserResult)) {
                int leftLimit = 48; // '0'
                int rightLimit = 122; // 'z'
                int targetStringLength = 10;
                Random random = new Random();
                String tempPassword = random.ints(leftLimit, rightLimit + 1)
                        .limit(targetStringLength)
                        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                        .toString();

                adminUserPort.setTempPasswordToRedis(
                        tempPassword,
                        command.getEmail()
                );

                adminUserPort.sendTempPasswordByEmail(
                        tempPassword,
                        command.getEmail()
                );
            }
        } catch (ApiExceptionImpl e) {
            throw new ApiExceptionImpl(ErrorCodeImpl.INTERNAL_SERVER);
        }

        return true;
    }

    @Override
    public boolean checkAdminUserTempPassword(AdminUserCheckTempPasswordCommand command) {
        boolean result = false;
        try {
            AdminfindPasswordResult adminfindPasswordResult = adminUserPort.getTempPasswordToRedis(
                    command.getEmail()
            );

            if (!ObjectUtils.isEmpty(adminfindPasswordResult)) {
                String tempPassword = adminfindPasswordResult.getTempPassword();
                if (!tempPassword.isEmpty() && tempPassword.equals(command.getTempPassword())) {
                    result = true;
                }
            }
        } catch (ApiExceptionImpl e) {
            throw new ApiExceptionImpl(UserErrorCodeImpl.USER_NOT_MATH);
        }
        return result;
    }
}
