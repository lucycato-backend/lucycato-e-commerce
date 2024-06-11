package org.lucycato.gatewayserver.application.port.in;

import reactor.core.publisher.Mono;

public interface AuthGlobalFilterUseCase {
    Mono<String> parseJwtToAdminUserJson(String token);

    Mono<String> parseJwtToAppUserJson(String token);
}
