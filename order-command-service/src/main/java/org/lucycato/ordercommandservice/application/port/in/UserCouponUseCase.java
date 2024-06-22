package org.lucycato.ordercommandservice.application.port.in;

import org.lucycato.ordercommandservice.application.port.in.command.coupon.AdminUserCouponCreateCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.UserCouponCreateCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.AdminUserCouponDeleteCommand;
import org.lucycato.ordercommandservice.domain.coupon.UserCoupon;

import java.util.List;

public interface UserCouponUseCase {
    UserCoupon createUserCoupon(UserCouponCreateCommand.ById command);
    UserCoupon createUserCoupon(UserCouponCreateCommand.ByCode command);
    List<UserCoupon> createUserCouponsAdmin(AdminUserCouponCreateCommand command) throws Exception;
    Long deleteUserCoupon(AdminUserCouponDeleteCommand command) throws Exception;
}
