package org.lucycato.ordercommandservice.application.port.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.ordercommandservice.application.port.in.CouponUseCase;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.*;
import org.lucycato.ordercommandservice.application.port.out.AdminUserPort;
import org.lucycato.ordercommandservice.application.port.out.CouponPort;
import org.lucycato.ordercommandservice.application.port.out.result.CouponResult;
import org.lucycato.ordercommandservice.domain.coupon.Coupon;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CouponService implements CouponUseCase {
    private final AdminUserPort adminUserPort;
    private final CouponPort couponPort;

    @Override
    public Coupon createCoupon(CouponCreateCommand command) throws Exception {
        boolean hasAdminRight = adminUserPort.hasAdminRights(command.getAdminUserId());
        if (!hasAdminRight) {
            throw new ApiExceptionImpl(ErrorCodeImpl.NOT_ROLE);
        }
        CouponResult couponResult = couponPort.save(
                command.getName(),
                command.getPromotionCode(),
                command.getDiscountStartAt(),
                command.getDiscountEndAt(),
                command.getComponents()
        );
        return Coupon.from(couponResult);
    }

    @Override
    public Coupon updateCoupon(CouponUpdateCommand command) throws Exception {
        boolean hasAdminRight = adminUserPort.hasAdminRights(command.getAdminUserId());
        if (!hasAdminRight) {
            throw new ApiExceptionImpl(ErrorCodeImpl.NOT_ROLE);
        }
        CouponResult couponResult = couponPort.update(
                command.getCouponId(),
                command.getName(),
                command.getPromotionCode(),
                command.getDiscountStartAt(),
                command.getDiscountEndAt(),
                command.getComponents()
        );
        return Coupon.from(couponResult);
    }
}
