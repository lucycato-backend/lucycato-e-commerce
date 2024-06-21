package org.lucycato.productqueryservice.application.port.out;

import reactor.core.publisher.Mono;

public interface UserAuthPort {

    Mono<Boolean> checkAuthToChangeTeacher(Long adminUserId);
}
