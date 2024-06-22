package org.lucycato.ordercommandservice.domain.coupon;

import lombok.*;
import org.lucycato.ordercommandservice.application.port.out.result.CouponResult;
import org.lucycato.ordercommandservice.domain.Order;
import org.lucycato.ordercommandservice.domain.enums.CouponType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeSet;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE, toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Coupon {
    private final Long couponId;
    private final String name;
    private final Boolean status;
    private final String promotionCode;
    private final LocalDateTime discountStartAt;
    private final LocalDateTime discountEndAt;
    private final List<CouponComponent> components;

    public static Coupon from(CouponResult couponResult) {
        return Coupon.builder()
                .build();
    }

    public static Coupon create(Long couponId, String name, Boolean status, String promotionCode, LocalDateTime discountStartAt, LocalDateTime discountEndAt, List<CouponComponent> components) {
        return Coupon.builder()
                .couponId(couponId)
                .name(name)
                .status(status)
                .promotionCode(promotionCode)
                .discountStartAt(discountStartAt)
                .discountEndAt(discountEndAt)
                .components(components)
                .build();
    }

    public boolean isValid() {
        return status && LocalDateTime.now().isAfter(discountStartAt) && LocalDateTime.now().isBefore(discountEndAt);
    }
}

