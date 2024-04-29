package org.lucycato.userservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class AppUserAdditionalInfoRequest {
    private String name;

    private String nickName;

    private String email;

    private String phoneNumber;
}
