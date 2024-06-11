package org.lucycato.gatewayservice.application.port.`in`

import reactor.core.publisher.Mono

interface AuthGlobalFilterUseCase {
    fun parseJwtToAdminUserJson(token: String?): Mono<String>

    fun parseJwtToAppUserJson(token: String?): Mono<String>
}