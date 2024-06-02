package org.lucycato.userauthcommandservice.application.port.out.result;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppleAccountResult {
    private String email;

    private String fullName;
}
