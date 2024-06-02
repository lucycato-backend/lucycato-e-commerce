package org.lucycato.userauthcommandservice.application.port.in;

import org.lucycato.userauthcommandservice.application.port.in.command.*;
import org.lucycato.userauthcommandservice.domain.AppUser;
import org.lucycato.userauthcommandservice.domain.AppUserMembership;
import org.lucycato.userauthcommandservice.domain.AppUserLogin;

public interface AppUserUseCase {
    AppUserLogin loginByAppleLogin(AppUserLoginByAppleSocialCommand command);

    AppUserLogin loginByKakaoSocial(AppUserLoginByKakaoSocialCommand command);

    AppUser verifyPhoneNumber(VerifyPhoneNumberCommand command);

    void loginCheck(AppUserLoginCheckCommand command);

    void logout(AppUserLogoutCommand command);

    AppUserMembership registerAppUserMembership(RegisterAppUserMembershipCommand command);

    void safeRemoveAppUserMembership(SafeRemoveAppUserMembershipCommand command);

    void updateAgreeMarketingTerms(AgreeMarketingTermsCommand command);
}
