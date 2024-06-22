package org.lucycato.gatewayservice.application.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.lucycato.gatewayservice.application.port.`in`.AuthGlobalFilterUseCase
import org.lucycato.gatewayservice.application.port.out.UserAuthPort
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class AuthGlobalFilterService(
        private val objectMapper: ObjectMapper,
        private val userAuthPort: UserAuthPort
) : AuthGlobalFilterUseCase {
    override fun parseJwtToAdminUserJson(token: String?): Mono<String> = if (token != null && token.startsWith("Bearer ")) {
        userAuthPort.getAdminUserAuthByToken(token)
                .flatMap { adminUserAuth ->
                    Mono.fromCallable { objectMapper.writeValueAsString(adminUserAuth) }
                }
    } else {
        Mono.empty()
    }

    override fun parseJwtToAppUserJson(token: String?): Mono<String> = if (token != null && token.startsWith("Bearer ")) {
        userAuthPort.getAppUserAuthByToken(token)
                .flatMap { appUserAuth ->
                    Mono.fromCallable { objectMapper.writeValueAsString(appUserAuth) }
                }
    } else {
        Mono.empty()
    }

}