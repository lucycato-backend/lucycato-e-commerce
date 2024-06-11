package org.lucycato.gatewayserver.application.port.out;

import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import reactor.core.publisher.Mono;

public interface UserAuthPort {
    Mono<AdminUserHeaderDetail> getAdminUserAuthByToken(String token);

    Mono<AppUserHeaderDetail> getAppUserAuthByToken(String token);
}
