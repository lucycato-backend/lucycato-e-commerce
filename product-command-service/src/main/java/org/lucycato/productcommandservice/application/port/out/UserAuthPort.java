package org.lucycato.productcommandservice.application.port.out;

public interface UserAuthPort {

    Boolean checkAuthToChangeTeacher(Long adminUserId, Long teacherId);
}

