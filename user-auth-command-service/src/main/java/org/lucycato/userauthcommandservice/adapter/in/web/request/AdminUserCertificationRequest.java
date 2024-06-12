package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AdminUserCertificationRequest {
    private String name;
    private String phoneNumber;
}
