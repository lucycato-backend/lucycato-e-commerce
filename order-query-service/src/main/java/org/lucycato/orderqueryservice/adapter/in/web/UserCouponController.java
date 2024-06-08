package org.lucycato.orderqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserCouponController {
    @GetMapping("api/app/v1/user-coupons")
    public void getUserCoupons() {

    }

    @GetMapping("api/app/v1/user-coupons/{userCouponId}")
    public void getUserCoupon(@PathVariable Long userCouponId) {

    }

    @GetMapping("api/admin/v1/user-coupons")
    public void getUserCouponsAdmin() {

    }

    @GetMapping("api/admin/v1/user-coupons/{userCouponId}")
    public void getUserCouponAdmin(@PathVariable Long userCouponId) {

    }
}
