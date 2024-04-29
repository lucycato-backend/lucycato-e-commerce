package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AppUser;
import org.lucycato.userservice.domain.AppUserMembership;
import org.lucycato.userservice.domain.DeviceManagement;

public interface AppUserUseCase {
    AppUser loginByAppleLogin(AppUserLoginByAppleSocialCommand command);

    AppUser loginByGoogleSocial(AppUserLoginByGoogleSocialCommand command);

    AppUser loginByNaverSocial(AppUserLoginByNaverSocialCommand command);

    AppUser loginByKakaoSocial(AppUserLoginByKakaoSocialCommand command);

    AppUser loginCheck(AppUserLoginCheckCommand command);

    AppUser logout(AppUserLogoutCommand command);

    AppUser enterAppUserAdditionalInfo(AppUserAdditionalInfoCommand command);

    AppUser registerAppUserMembership(RegisterAppUserMembershipCommand command);

    AppUser safeRemoveAppUserMembership(SafeRemoveAppUserMembershipCommand command);

    AppUser getAppUser(GetAppUserCommand command);

    DeviceManagement getAppUserDeviceManagement(GetAppUserDeviceManagementCommand command);

    AppUserMembership getAppUserMembership(GetAppUserMembershipCommand command);
}
