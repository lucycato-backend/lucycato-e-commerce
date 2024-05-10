package org.lucycato.userservice.application.port.in;

import org.lucycato.userservice.application.port.in.command.*;
import org.lucycato.userservice.domain.AppUser;
import org.lucycato.userservice.domain.AppUserMembership;
import org.lucycato.userservice.domain.AppUserLogin;

public interface AppUserUseCase {
    AppUserLogin loginByAppleLogin(AppUserLoginByAppleSocialCommand command);

    AppUserLogin loginByKakaoSocial(AppUserLoginByKakaoSocialCommand command);

    AppUser verifyPhoneNumber(VerifyPhoneNumberCommand command);

    void loginCheck(AppUserLoginCheckCommand command);

    void logout(AppUserLogoutCommand command);

    AppUserMembership registerAppUserMembership(RegisterAppUserMembershipCommand command);

    void safeRemoveAppUserMembership(SafeRemoveAppUserMembershipCommand command);

    void addAppUserCareer(AppUserAdditionalCareerCommand command);
}
