package org.lucycato.ordercommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartController {
    @PostMapping("api/app/v1/carts")
    public void createCart(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        Long appUserId = appUserHeaderDetail.getAppUserId();

    }

    @PatchMapping("api/app/v1/carts/{cartId}")
    public void updateCart(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail,
                           @PathVariable Long cartId) {
        Long appUserId = appUserHeaderDetail.getAppUserId();
    }

    @DeleteMapping("api/app/v1/carts")
    public void deleteCarts(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail) {
        Long appUserId = appUserHeaderDetail.getAppUserId();
    }

    @DeleteMapping("api/app/v1/carts/{cartId}")
    public void deleteCart(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail,
                           @PathVariable Long cartId) {
        Long appUserId = appUserHeaderDetail.getAppUserId();
    }
}
