package org.lucycato.productqueryservice.application.port.out;

import reactor.core.publisher.Mono;

public interface OrderPort {

    Mono<Boolean> checkAppUserBuyCourse(Long appUserId, Long courseId);
}
