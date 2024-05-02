package org.lucycato.userservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucycato.userservice.adapter.out.persistence.entity.converter.AppUserBadgeListConverter;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.adapter.out.persistence.entity.converter.DeviceInfoListConverter;
import org.lucycato.userservice.domain.enums.AppUserBadge;
import org.lucycato.userservice.domain.enums.AppUserGrade;
import org.lucycato.userservice.domain.enums.AppUserStatus;
import org.lucycato.userservice.domain.enums.SocialStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "app_user")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class AppUserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SocialStatus socialStatus;

    private String name;

    private String email;

    private String phoneNumber;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private AppUserGrade grade;

    @Convert(converter = AppUserBadgeListConverter.class)
    @Column(columnDefinition = "JSON")
    private List<AppUserBadge> appUserBadges;

    @Convert(converter = DeviceInfoListConverter.class)
    @Column(columnDefinition = "JSON")
    private List<DeviceVo> deviceVos;

    @Enumerated(EnumType.STRING)
    private AppUserStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    @OneToMany(mappedBy = "appUserJpaEntity", fetch = FetchType.LAZY)
    private List<AppUserMembershipJpaEntity> appUserMembershipJpaEntities;

    public AppUserJpaEntity(SocialStatus socialStatus, String name, String email, String phoneNumber, AppUserStatus status) {
        this.socialStatus = socialStatus;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.status = status;
        this.appUserBadges = new ArrayList<>();
        this.deviceVos = new ArrayList<>();
    }
}
