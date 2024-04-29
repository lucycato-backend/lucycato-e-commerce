package org.lucycato.userservice.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.lucycato.userservice.model.enums.AppUserBadge;
import org.lucycato.userservice.model.enums.AppUserGrade;
import org.lucycato.userservice.model.enums.SocialStatus;
import org.lucycato.userservice.model.info.DeviceInfo;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "app_user")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SocialStatus socialStatus;

    private String nickName;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String imageUrl;

    private AppUserGrade grade;

    private List<AppUserBadge> appUserBadges;

    @Convert(converter = DeviceInfoListConverter.class)
    @Column(columnDefinition = "JSON")
    private List<DeviceInfo> deviceInfos;

    private LocalDateTime lastLoginAt;

    private LocalDateTime lastLogoutAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public AppUserJpaEntity(SocialStatus socialStatus, String nickName, String name, String email, String phoneNumber, String imageUrl, AppUserGrade grade, List<AppUserBadge> appUserBadges, List<DeviceInfo> deviceInfos, LocalDateTime lastLoginAt, LocalDateTime lastLogoutAt, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.socialStatus = socialStatus;
        this.nickName = nickName;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.imageUrl = imageUrl;
        this.grade = grade;
        this.appUserBadges = appUserBadges;
        this.deviceInfos = deviceInfos;
        this.lastLoginAt = lastLoginAt;
        this.lastLogoutAt = lastLogoutAt;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
