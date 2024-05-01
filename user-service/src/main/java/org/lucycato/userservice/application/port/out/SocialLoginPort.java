package org.lucycato.userservice.application.port.out;

import org.lucycato.userservice.application.port.out.result.AppleAccountResult;
import org.lucycato.userservice.application.port.out.result.KakaoAccountResult;

public interface SocialLoginPort {
    KakaoAccountResult getKakaoAccount(String code);

    AppleAccountResult getAppleAccount(String code);
}
