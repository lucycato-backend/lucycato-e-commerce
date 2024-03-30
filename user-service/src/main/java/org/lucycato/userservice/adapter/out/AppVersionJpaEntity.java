package org.lucycato.userservice.adapter.out;

import jakarta.persistence.*;
import jdk.jfr.Enabled;
import lombok.*;

@Table(name = "app_version")
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppVersionJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String appVersion;

    public AppVersionJpaEntity(String appVersion) {
        this.appVersion = appVersion;
    }
}
