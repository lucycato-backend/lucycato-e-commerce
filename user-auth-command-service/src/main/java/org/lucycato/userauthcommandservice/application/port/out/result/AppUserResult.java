package org.lucycato.userauthcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userauthcommandservice.adapter.out.persistence.entity.AppUserJpaEntity;
import org.lucycato.userauthcommandservice.domain.enums.AppUserBadge;
import org.lucycato.userauthcommandservice.domain.enums.AppUserGrade;
import org.lucycato.userauthcommandservice.domain.enums.AppUserStatus;
import org.lucycato.userauthcommandservice.domain.enums.SocialStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResult {
    private Long appUserId;

    private AppUserStatus status;

    private SocialStatus socialStatus;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String imageUrl;

    private AppUserGrade grade;

    private List<AppUserBadge> badges;

    private List<AppUserMembershipResult> membershipResults;

    private LocalDateTime createdAt;

    public static AppUserResult from(AppUserJpaEntity entity) {
        List<AppUserMembershipResult> appUserMembershipResults = entity.getAppUserMembershipJpaEntities()
                .stream()
                .map(AppUserMembershipResult::from)
                .toList();

        return AppUserResult.builder()
                .appUserId(entity.getId())
                .status(entity.getStatus())
                .socialStatus(entity.getSocialStatus())
                .name(entity.getName())
                .email(entity.getEmail())
                .phoneNumber(entity.getPhoneNumber())
                .imageUrl(entity.getImageUrl())
                .grade(entity.getGrade())
                .badges(entity.getAppUserBadges())
                .membershipResults(appUserMembershipResults)
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
