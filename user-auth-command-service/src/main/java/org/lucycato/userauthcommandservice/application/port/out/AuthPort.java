package org.lucycato.userauthcommandservice.application.port.out;

import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userauthcommandservice.application.port.out.result.IssueJwtTokenResult;
import org.lucycato.userauthcommandservice.domain.enums.PlatformType;

import java.util.List;

public interface AuthPort {
    Boolean verifyPhoneNumberAuthCode(String phoneNumberAuthCode);

    IssueJwtTokenResult issueAdminUserJwtToken(
            Long adminUserId, List<AdminUserRole> adminUserRoles,
            String currentDeviceMacAddress,
            PlatformType currentPlatformType
    );

    IssueJwtTokenResult issueAppUserJwtToken(
            Long adminUserId,
            String currentDeviceMacAddress,
            PlatformType currentPlatformType
    );
}
