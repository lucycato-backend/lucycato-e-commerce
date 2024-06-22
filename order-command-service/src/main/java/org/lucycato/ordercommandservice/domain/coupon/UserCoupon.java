package org.lucycato.ordercommandservice.domain.coupon;

import lombok.*;
import org.lucycato.ordercommandservice.application.port.out.result.UserCouponResult;
import org.lucycato.ordercommandservice.domain.coupon.Coupon;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter(value = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE, toBuilder = true)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCoupon {
    private final Long userCouponId;
    private final Boolean isUsed;
    private final LocalDateTime issuedAt;
    private final LocalDateTime usedAt;
    private final Coupon coupon;
    private final Long userId;

    public static UserCoupon to (UserCouponResult result) {
        return UserCoupon.builder()
                .build();
    }

    public static UserCoupon create(Coupon coupon, Long userId) {
        return UserCoupon.builder()
                .isUsed(false)
                .issuedAt(LocalDateTime.now())
                .usedAt(null)
                .coupon(coupon)
                .userId(userId)
                .build();
    }

    public boolean isValid() {
        return !isUsed && coupon.isValid();
    }

    public UserCoupon markAsUsed() {
        if (!isValid()) {
            throw new IllegalStateException("Coupon is not valid or already used");
        }
        return this.toBuilder()
                .isUsed(false)
                .usedAt(LocalDateTime.now())
                .build();
    }
}
