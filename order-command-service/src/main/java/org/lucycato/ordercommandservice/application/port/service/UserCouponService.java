package org.lucycato.ordercommandservice.application.port.service;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.ordercommandservice.application.port.in.UserCouponUseCase;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.AdminUserCouponCreateCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.AdminUserCouponDeleteCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.UserCouponCreateCommand;
import org.lucycato.ordercommandservice.application.port.out.AdminUserPort;
import org.lucycato.ordercommandservice.application.port.out.CouponPort;
import org.lucycato.ordercommandservice.application.port.out.UserCouponPort;
import org.lucycato.ordercommandservice.application.port.out.result.UserCouponResult;
import org.lucycato.ordercommandservice.domain.coupon.Coupon;
import org.lucycato.ordercommandservice.domain.coupon.UserCoupon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCouponService implements UserCouponUseCase {
    private final AdminUserPort adminUserPort;
    private final CouponPort couponPort;
    private final UserCouponPort userCouponPort;

    @Override
    public UserCoupon createUserCoupon(UserCouponCreateCommand.ById command) {
        Coupon coupon = Coupon.from(couponPort.findCouponById(command.getCouponId()));
        UserCoupon userCoupon = UserCoupon.create(coupon, command.getAppUserId());
        UserCouponResult userCouponResult = userCouponPort.save(
                userCoupon.getIsUsed(),
                userCoupon.getIssuedAt(),
                userCoupon.getUsedAt(),
                userCoupon.getCoupon(),
                userCoupon.getUserId()
        );
        return UserCoupon.to(userCouponResult);
    }

    @Override
    public UserCoupon createUserCoupon(UserCouponCreateCommand.ByCode command) {
        Coupon coupon = Coupon.from(couponPort.findCouponByCode(command.getPromotionCode()));
        UserCoupon userCoupon = UserCoupon.create(coupon, command.getAppUserId());
        UserCouponResult userCouponResult = userCouponPort.save(
                userCoupon.getIsUsed(),
                userCoupon.getIssuedAt(),
                userCoupon.getUsedAt(),
                userCoupon.getCoupon(),
                userCoupon.getUserId()
        );
        return UserCoupon.to(userCouponResult);
    }

    @Override
    public List<UserCoupon> createUserCouponsAdmin(AdminUserCouponCreateCommand command) throws Exception {
        boolean hasAdminRight = adminUserPort.hasAdminRights(command.getAdminUserId());
        if (!hasAdminRight) {
            throw new ApiExceptionImpl(ErrorCodeImpl.NOT_ROLE);
        }
        Coupon coupon = Coupon.from(couponPort.findCouponById(command.getCouponId()));
        List<Long> appUserIds = command.getAppUserIds();
        return appUserIds.stream()
                .map(appUserId -> {
                    UserCoupon userCoupon = UserCoupon.create(coupon, appUserId);
                    UserCouponResult userCouponResult = userCouponPort.save(
                            userCoupon.getIsUsed(),
                            userCoupon.getIssuedAt(),
                            userCoupon.getUsedAt(),
                            userCoupon.getCoupon(),
                            userCoupon.getUserId()
                    );
                    return UserCoupon.to(userCouponResult);
                })
                .toList();

    }

    @Override
    public Long deleteUserCoupon(AdminUserCouponDeleteCommand command) throws Exception {
        boolean hasAdminRight = adminUserPort.hasAdminRights(command.getAdminUserId());
        if (!hasAdminRight) {
            throw new ApiExceptionImpl(ErrorCodeImpl.NOT_ROLE);
        }
        return userCouponPort.deleteByUserCouponId(command.getUserCouponId());
    }
}
