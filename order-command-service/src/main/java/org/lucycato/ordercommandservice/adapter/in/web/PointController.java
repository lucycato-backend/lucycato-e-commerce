package org.lucycato.ordercommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PointController {
    @PostMapping("api/app/v1/points")
    public void changePoint(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        Long appUserId = appUserHeaderDetail.getAppUserId();
    }
    @PostMapping("api/admin/v1/users/{userId}/points/change")
    public void changePoint(@PathVariable Long userId) {

    }
}
