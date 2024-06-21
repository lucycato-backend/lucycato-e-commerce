package org.lucycato.ordercommandservice.application.port.in;

import org.lucycato.ordercommandservice.application.port.in.command.coupon.*;
import org.lucycato.ordercommandservice.domain.coupon.Coupon;

public interface CouponUseCase {
    Coupon createCoupon(CouponCreateCommand command) throws Exception;
    Coupon updateCoupon(CouponUpdateCommand command) throws Exception;
}
