package org.lucycato.eventcommandservice

import org.lucycato.common.annotation.hexagonal.`in`.WebAdapter
import org.lucycato.common.annotation.resolver.AdminUserHeaders
import org.lucycato.common.annotation.resolver.AppUserHeaders
import org.lucycato.common.resolver.AdminUserHeaderDetail
import org.lucycato.common.resolver.AppUserHeaderDetail
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@WebAdapter
@RestController
class EventController {

    @GetMapping("open-api/v1/test")
    fun test() : Mono<String> {
        return Mono.just("Hello")
                .map {
                    "$it Kotlin Good"
                }
    }

    @GetMapping("api/admin/v1/test")
    fun test(@AdminUserHeaders adminUserHeaderDetail: AdminUserHeaderDetail) : Mono<String> {
        return Mono.just("Hello kotlin2 ${adminUserHeaderDetail.adminUserId}")
    }

    @GetMapping("api/app/v1/test")
    fun test(@AppUserHeaders appUserHeaderDetail: AppUserHeaderDetail) : Mono<String> {
        return Mono.just("Hello kotlin3 ${appUserHeaderDetail.appUserId}")
    }

}