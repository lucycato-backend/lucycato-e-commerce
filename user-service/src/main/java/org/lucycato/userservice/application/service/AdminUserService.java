package org.lucycato.userservice.application.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.UserErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.userservice.application.port.in.AdminUserUseCase;
import org.lucycato.userservice.application.port.in.command.AdminUserLoginCommand;
import org.lucycato.userservice.application.port.in.command.AdminUserLogoutCommand;
import org.lucycato.userservice.application.port.in.command.AdminUserRegisterCommand;
import org.lucycato.userservice.application.port.in.command.ModifyAdminUserRoleCommand;
import org.lucycato.userservice.application.port.out.AdminUserPort;
import org.lucycato.userservice.application.port.out.result.AdminUserResult;
import org.lucycato.userservice.application.port.out.result.IssueFcmTokenResult;
import org.lucycato.userservice.application.port.out.AuthPort;
import org.lucycato.userservice.domain.AdminUser;
import org.lucycato.userservice.domain.AdminUserLogin;
import org.lucycato.userservice.model.enums.AppUserGrade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AdminUserService implements AdminUserUseCase {

    private final AdminUserPort adminUserPort;

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
                    command.getPhoneNumber(),
                    command.getDeviceMacAddress(),
                    command.getDeviceFcmToken(),
                    command.getDeviceOsType(),
                    command.getDeiceOsVersion()
            );

            IssueFcmTokenResult issueFcmTokenResult = authPort.issueAdminUserFcmToken(
                    adminUserResult.getAdminUserId(),
                    adminUserResult.getAdminUserRoles()
            );

            return AdminUserLogin.create(
                    adminUserResult.getAdminUserId(),
                    issueFcmTokenResult.getAccessToken(),
                    issueFcmTokenResult.getExpiredAccessToken(),
                    issueFcmTokenResult.getRefreshToken(),
                    issueFcmTokenResult.getExpiredRefreshToken()
            );
        } else {
            throw new ApiExceptionImpl(UserErrorCodeImpl.PHONE_NUMBER_VERIFICATION_NOT_MATCH);
        }
    }

    @Override
    public AdminUserLogin login(AdminUserLoginCommand command) {
        AdminUserResult adminUserResult;
        try {
             adminUserResult = adminUserPort.getAdminUserResult(command.getEmail(), command.getPassword());
        } catch (Exception e) {
            throw new ApiExceptionImpl(UserErrorCodeImpl.USER_NOT_MATH);
        }

        adminUserPort.modifyDeviceInfo(
                adminUserResult.getAdminUserId(),
                command.getDeviceMacAddress(),
                command.getDeviceFcmToken(),
                command.getDeviceOsType(),
                command.getDeiceOsVersion()
        );

        IssueFcmTokenResult issueFcmTokenResult = authPort.issueAdminUserFcmToken(
                adminUserResult.getAdminUserId(),
                adminUserResult.getAdminUserRoles()
        );

        return AdminUserLogin.create(
                adminUserResult.getAdminUserId(),
                issueFcmTokenResult.getAccessToken(),
                issueFcmTokenResult.getExpiredAccessToken(),
                issueFcmTokenResult.getRefreshToken(),
                issueFcmTokenResult.getExpiredRefreshToken()
        );
    }

    @Override
    public void logout(AdminUserLogoutCommand command) {
        adminUserPort.expireAdminUser(command.getAdminMemberId());
    }

    @Override
    public AdminUser addAdminUserRole(ModifyAdminUserRoleCommand command) {
        AdminUserResult adminUserResult = adminUserPort.addAdminUserRole(
                command.getTargetAdminUserId(),
                command.getTargetChangeRole()
        );

        return AdminUser.create(
                adminUserResult.getAdminUserId(),
                adminUserResult.getName(),
                adminUserResult.getEmail(),
                adminUserResult.getPhoneNumber(),
                adminUserResult.getImageUrl(),
                adminUserResult.getAdminUserRoles(),
                adminUserResult.getLastLoginAt(),
                adminUserResult.getLastLogoutAt(),
                adminUserResult.getCreatedAt(),
                adminUserResult.getModifiedAt()
        );
    }

    @Override
    public AdminUser removeAdminUserRole(ModifyAdminUserRoleCommand command) {
        AdminUserResult adminUserResult = adminUserPort.removeAdminUserRole(
                command.getTargetAdminUserId(),
                command.getTargetChangeRole()
        );
        return AdminUser.create(
                adminUserResult.getAdminUserId(),
                adminUserResult.getName(),
                adminUserResult.getEmail(),
                adminUserResult.getPhoneNumber(),
                adminUserResult.getImageUrl(),
                adminUserResult.getAdminUserRoles(),
                adminUserResult.getLastLoginAt(),
                adminUserResult.getLastLogoutAt(),
                adminUserResult.getCreatedAt(),
                adminUserResult.getModifiedAt()
        );
    }
}
