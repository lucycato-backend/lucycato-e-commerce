package org.lucycato.ordercommandservice.application.port.out;

import org.lucycato.ordercommandservice.application.port.in.command.coupon.component.CouponComponentCommand;
import org.lucycato.ordercommandservice.application.port.out.result.CouponResult;
import org.lucycato.ordercommandservice.application.port.out.result.UserCouponResult;
import org.lucycato.ordercommandservice.domain.coupon.Coupon;

import java.time.LocalDateTime;
import java.util.List;

public interface UserCouponPort {
    UserCouponResult save(Boolean isUsed,
                          LocalDateTime issuedAt,
                          LocalDateTime usedAt,
                          Coupon coupon,
                          Long userId);

    Long deleteByUserCouponId(Long userCouponId);
}
