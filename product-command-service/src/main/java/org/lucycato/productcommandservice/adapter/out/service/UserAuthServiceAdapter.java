package org.lucycato.productcommandservice.adapter.out.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter;
import org.lucycato.productcommandservice.application.port.out.UserAuthPort;

@ServiceAdapter
@RequiredArgsConstructor
public class UserAuthServiceAdapter implements UserAuthPort {

    @Override
    public Boolean checkAuthToChangeTeacher(
            Long adminUserId,
            Long teacherId
    ) {
        return true;
    }
}
