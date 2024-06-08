package org.lucycato.orderqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CouponController {
    @GetMapping("/open-api/v1/coupons")
    public void getCoupons() {
    }

    @GetMapping("/open-api/v1/coupons/{couponId}")
    public void getCoupon(@PathVariable Long couponId) {
    }
}
