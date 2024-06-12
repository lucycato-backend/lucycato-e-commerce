package org.lucycato.userauthcommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserAdditionalInfoRequest {
    private String name;

    private String email;

    private String phoneNumber;
}
