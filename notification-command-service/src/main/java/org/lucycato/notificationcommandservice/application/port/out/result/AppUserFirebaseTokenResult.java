package org.lucycato.notificationcommandservice.application.port.out.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUserFirebaseTokenResult {

    private Long appUserId;

    // TODO: User 테이블에 Firebase Token field 추가 필요
    private String appUserToken;
}
