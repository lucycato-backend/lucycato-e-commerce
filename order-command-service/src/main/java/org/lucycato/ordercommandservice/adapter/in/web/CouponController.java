package org.lucycato.ordercommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponController {
    @PostMapping("api/admin/v1/coupons")
    public void createCoupons() {

    }

    @PatchMapping("api/admin/v1/coupons/{couponId}")
    public void updateCoupon(@PathVariable Long couponId) {

    }
}
