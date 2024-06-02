package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FindAdminUserIdRequest {
    private String name;
    private String phoneNumber;
    private String verifyPhoneNumberAuthCode;
}
