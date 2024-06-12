package org.lucycato.userauthcommandservice.application.port.out;

import org.lucycato.userauthcommandservice.application.port.out.result.AppUserMarketingConsentResult;
import org.lucycato.userauthcommandservice.application.port.out.result.AppUserMembershipResult;
import org.lucycato.userauthcommandservice.application.port.out.result.AppUserResult;
import org.lucycato.userauthcommandservice.domain.enums.AppUserStatus;
import org.lucycato.userauthcommandservice.domain.enums.MembershipGrade;
import org.lucycato.userauthcommandservice.domain.enums.SocialStatus;

import java.time.LocalDateTime;

public interface AppUserPort {
    AppUserResult registerAppUser(
            SocialStatus socialStatus,
            String name,
            String email,
            String phoneNumber,
            AppUserStatus status
    );

    AppUserResult modifyAppUser(
            Long appUserId,
            String phoneNumber,
            AppUserStatus appUserStatus
    );

    void expiredAppUser(Long appUserId);

    AppUserMembershipResult registerAppUserMembership(
            Long appUserId,
            MembershipGrade membershipGrade,
            LocalDateTime expiredAt
    );

    AppUserMembershipResult safeRemoveAppUserMembership(
            Long appUserId,
            Long appUserMembershipId
    );

    AppUserMarketingConsentResult updateAgreeMarketingTerms(
            Long appUserId,
            Boolean isAgreeMarketingTerms
    );
}
