package org.lucycato.userservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.userservice.adapter.out.persistence.entity.AppUserMembershipJpaEntity;
import org.lucycato.userservice.domain.enums.MembershipGrade;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserMembershipResult {
    private Long appUserMembershipId;

    private Long appUserId;

    private MembershipGrade membershipGrade;

    private Boolean isExpired;

    private LocalDateTime createdAt;

    private LocalDateTime expiredAt;

    public static AppUserMembershipResult from(AppUserMembershipJpaEntity entity) {
        return AppUserMembershipResult.builder()
                .appUserMembershipId(entity.getId())
                .membershipGrade(entity.getMembershipGrade())
                .isExpired(LocalDateTime.now().isAfter(entity.getExpiredAt()))
                .createdAt(entity.getCreatedAt())
                .expiredAt(entity.getExpiredAt())
                .build();
    }
}
