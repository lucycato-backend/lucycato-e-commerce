package org.lucycato.userauthcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userauthcommandservice.adapter.out.persistence.entity.AppUserMembershipJpaEntity;
import org.lucycato.userauthcommandservice.domain.enums.AppUserMembershipStatus;
import org.lucycato.userauthcommandservice.domain.enums.MembershipGrade;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserMembershipResult {
    private Long appUserMembershipId;

    private AppUserMembershipStatus status;

    private Long appUserId;

    private MembershipGrade membershipGrade;

    private Boolean isExpired;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;

    public static AppUserMembershipResult from(AppUserMembershipJpaEntity entity) {
        return AppUserMembershipResult.builder()
                .appUserMembershipId(entity.getId())
                .status(entity.getStatus())
                .membershipGrade(entity.getMembershipGrade())
                .isExpired(LocalDateTime.now().isAfter(entity.getExpiredAt()))
                .createdAt(entity.getCreatedAt())
                .expiredAt(entity.getExpiredAt())
                .build();
    }
}
