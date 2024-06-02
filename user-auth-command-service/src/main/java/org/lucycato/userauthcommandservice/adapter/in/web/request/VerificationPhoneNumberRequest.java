package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class VerificationPhoneNumberRequest {
    private String phoneNumber;

    private String authorizedCode;
}
