package org.lucycato.userservice.application.port.out.result;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppleAccountResult {
    private String email;

    private String fullName;
}
