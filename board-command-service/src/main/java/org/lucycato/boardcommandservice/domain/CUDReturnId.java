package org.lucycato.boardcommandservice.domain;

import lombok.*;
import org.lucycato.boardcommandservice.application.port.out.result.CUDReturnIdResult;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class CUDReturnId {

    private final Long targetId;

    public static CUDReturnId from(CUDReturnIdResult result) {
        return CUDReturnId.builder()
                .targetId(result.getId())
                .build();
    }
}
