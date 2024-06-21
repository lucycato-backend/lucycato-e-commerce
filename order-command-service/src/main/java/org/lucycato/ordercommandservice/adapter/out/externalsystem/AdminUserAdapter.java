package org.lucycato.ordercommandservice.adapter.out.externalsystem;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.lucycato.mvc.CommonRedisTemplate;
import org.lucycato.mvc.CommonRestTemplate;
import org.lucycato.ordercommandservice.application.port.out.AdminUserPort;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class AdminUserAdapter implements AdminUserPort {
    private final CommonRestTemplate restTemplate;

    @Override
    public boolean hasAdminRights(Long userId) throws Exception {
        String url = "http://user-service/api/users/" + userId;
        UserPermissionResponse response = restTemplate.sendGetRequest(url, UserPermissionResponse.class);
        return response != null && response.isAdmin();
    }


    @NoArgsConstructor
    private static class UserPermissionResponse {
        private boolean isAdmin;

        public boolean isAdmin() {
            return isAdmin;
        }
    }
}
