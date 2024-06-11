package org.lucycato.ordercommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserCouponController {
    @PostMapping("api/app/v1/user-coupons")
    public void createUserCoupon() {

    }
    @PostMapping("api/admin/v1/user-coupons")
    public void createUserCouponAdmin() {

    }

    @PatchMapping("api/admin/v1/user-coupons/{userCouponId}")
    public void updateUserCoupon(@PathVariable Long userCouponId) {

    }
}
