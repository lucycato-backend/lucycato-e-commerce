package org.lucycato.gatewayservice.adapter.out.service

import org.lucycato.common.annotation.hexagonal.out.ServiceAdapter
import org.lucycato.common.resolver.AdminUserHeaderDetail
import org.lucycato.common.resolver.AppUserHeaderDetail
import org.lucycato.gatewayservice.application.port.out.UserAuthPort
import reactor.core.publisher.Mono

@ServiceAdapter
class UserAuthServiceAdapter : UserAuthPort {
    override fun getAdminUserAuthByToken(token: String): Mono<AdminUserHeaderDetail> {
        return Mono.just(AdminUserHeaderDetail(1L))
    }

    override fun getAppUserAuthByToken(token: String): Mono<AppUserHeaderDetail> {
        return Mono.just(AppUserHeaderDetail(1L))
    }
}