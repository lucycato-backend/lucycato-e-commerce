package org.lucycato.gatewayservice.application.port.out

import org.lucycato.common.resolver.AdminUserHeaderDetail
import org.lucycato.common.resolver.AppUserHeaderDetail
import reactor.core.publisher.Mono

interface UserAuthPort {
    fun getAdminUserAuthByToken(token: String) : Mono<AdminUserHeaderDetail>

    fun getAppUserAuthByToken(token: String) : Mono<AppUserHeaderDetail>
}