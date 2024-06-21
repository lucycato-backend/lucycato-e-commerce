package org.lucycato.ordercommandservice.adapter.out.persistence;

import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.ordercommandservice.application.port.out.CouponPort;
import org.lucycato.ordercommandservice.application.port.out.UserCouponPort;
import org.lucycato.ordercommandservice.application.port.out.result.UserCouponResult;
import org.lucycato.ordercommandservice.domain.coupon.Coupon;

import java.time.LocalDateTime;

@PersistenceAdapter
public class UserCouponAdapter implements UserCouponPort {
    @Override
    public UserCouponResult save(Boolean isUsed, LocalDateTime issuedAt, LocalDateTime usedAt, Coupon coupon, Long userId) {
        return null;
    }

    @Override
    public Long deleteByUserCouponId(Long userCouponId) {
        return null;
    }
}
