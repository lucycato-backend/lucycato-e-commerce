package org.lucycato.userservice.adapter.out.persistence.redisentity;

import lombok.*;
import org.lucycato.common.RedisEntity;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.AppUserJpaEntity;
import org.lucycato.userservice.domain.enums.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppUserRedisEntity implements RedisEntity {
    private Long id;

    private SocialStatus socialStatus;

    private String name;

    private String email;

    private String phoneNumber;

    private String imageUrl;

    private AppUserGrade grade;

    private List<AppUserBadge> appUserBadges;

    private AppUserStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime modifiedAt;

    public static AppUserRedisEntity from(AppUserJpaEntity entity) {
        return new AppUserRedisEntity(
                entity.getId(),
                entity.getSocialStatus(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhoneNumber(),
                entity.getImageUrl(),
                entity.getGrade(),
                entity.getAppUserBadges(),
                entity.getStatus(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }
}
