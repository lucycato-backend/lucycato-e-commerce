package org.lucycato.userservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.exec.spi.StandardEntityInstanceResolver;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KakaoAccountResult {
    private String email;

    private String name;

    private String phoneNumber;
}
