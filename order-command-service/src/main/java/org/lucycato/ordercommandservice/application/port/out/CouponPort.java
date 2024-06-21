package org.lucycato.ordercommandservice.application.port.out;

import org.lucycato.ordercommandservice.application.port.in.command.coupon.component.CouponComponentCommand;
import org.lucycato.ordercommandservice.application.port.out.result.CouponResult;
import org.lucycato.ordercommandservice.domain.enums.CouponType;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.TreeSet;

public interface CouponPort {
    CouponResult save(String name,
                      String promotionCode,
                      LocalDateTime discountStartAt,
                      LocalDateTime discountEndAt,
                      List<CouponComponentCommand> components);

    CouponResult update(Long couponId,
                        String name,
                        String promotionCode,
                        LocalDateTime discountStartAt,
                        LocalDateTime discountEndAt,
                        List<CouponComponentCommand> components);

    CouponResult findCouponById(Long couponId);
    CouponResult findCouponByCode(String code);
}
