package org.lucycato.userservice.adapter.out.persistence.redisentity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.lucycato.common.RedisEntity;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.AppUserMembershipJpaEntity;
import org.lucycato.userservice.domain.enums.AppUserMembershipStatus;
import org.lucycato.userservice.domain.enums.MembershipGrade;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserMembershipRedisEntity implements RedisEntity {
    private Long id;

    private Long appUserId;

    private MembershipGrade membershipGrade;

    private LocalDateTime expiredAt;

    private AppUserMembershipStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public static AppUserMembershipRedisEntity from(AppUserMembershipJpaEntity entity) {
        return new AppUserMembershipRedisEntity(
            entity.getId(),
            entity.getAppUserJpaEntity().getId(),
            entity.getMembershipGrade(),
            entity.getExpiredAt(),
            entity.getStatus(),
            entity.getCreatedAt(),
            entity.getModifiedAt()
        );
    }
}
