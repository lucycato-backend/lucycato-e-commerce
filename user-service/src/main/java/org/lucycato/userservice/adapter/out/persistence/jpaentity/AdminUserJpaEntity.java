package org.lucycato.userservice.adapter.out.persistence.jpaentity;

import jakarta.persistence.*;
import lombok.*;
import org.lucycato.common.security.AdminUserRole;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.converter.AdminRoleListConverter;
import org.lucycato.userservice.adapter.out.persistence.vo.DeviceVo;
import org.lucycato.userservice.adapter.out.persistence.jpaentity.converter.DeviceInfoListConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "admin_user")
@Entity
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickName;

    private String name;

    private String email;

    private String password;

    private String phoneNumber;

    private String imageUrl;

    @Convert(converter = AdminRoleListConverter.class)
    @Column(columnDefinition = "JSON")
    private List<AdminUserRole> adminUserRoles;

    @Convert(converter = DeviceInfoListConverter.class)
    @Column(columnDefinition = "JSON")
    private List<DeviceVo> deviceVos;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime modifiedAt;

    public AdminUserJpaEntity(String nickName, String name, String email, String password, String phoneNumber) {
        this.nickName = nickName;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.adminUserRoles = new ArrayList<>();
        this.deviceVos = new ArrayList<>();
    }
}
