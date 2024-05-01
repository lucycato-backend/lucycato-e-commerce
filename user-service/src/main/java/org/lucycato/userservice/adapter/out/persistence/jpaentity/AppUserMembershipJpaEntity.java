package org.lucycato.userservice.adapter.out.persistence.jpaentity;

import jakarta.persistence.*;
import lombok.*;
import org.lucycato.userservice.domain.enums.AppUserMembershipStatus;
import org.lucycato.userservice.domain.enums.MembershipGrade;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Table(name = "app_user_membership")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class AppUserMembershipJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUserJpaEntity appUserJpaEntity;

    @Enumerated(EnumType.STRING)
    private MembershipGrade membershipGrade;

    private LocalDateTime expiredAt;

    @Enumerated(EnumType.STRING)
    private AppUserMembershipStatus status;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public AppUserMembershipJpaEntity(AppUserJpaEntity appUserJpaEntity, MembershipGrade membershipGrade, AppUserMembershipStatus status, LocalDateTime expiredAt) {
        this.appUserJpaEntity = appUserJpaEntity;
        this.membershipGrade = membershipGrade;
        this.status = status;
        this.expiredAt = expiredAt;
    }
}
