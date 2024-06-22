package org.lucycato.ordercommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.annotation.resolver.AppUserHeaders;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.common.resolver.AppUserHeaderDetail;
import org.lucycato.ordercommandservice.adapter.in.web.request.AdminUserCouponCreateRequest;
import org.lucycato.ordercommandservice.adapter.in.web.request.UserCouponCreateRequest;
import org.lucycato.ordercommandservice.application.port.in.UserCouponUseCase;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.AdminUserCouponCreateCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.AdminUserCouponDeleteCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.UserCouponCreateCommand;
import org.lucycato.ordercommandservice.domain.coupon.UserCoupon;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserCouponController {
    private final UserCouponUseCase userCouponUseCase;

    @PostMapping("api/app/v1/user-coupons")
    public UserCoupon publishUserCoupon(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail,
                                        @RequestBody UserCouponCreateRequest.ById request) {
        Long appUserId = appUserHeaderDetail.getAppUserId();
        UserCouponCreateCommand.ById command = new UserCouponCreateCommand.ById(
                appUserId,
                request.getCouponId()
        );
        return userCouponUseCase.createUserCoupon(command);
    }

    @PostMapping("api/app/v1/user-coupons/by-code")
    public UserCoupon publishUserCouponByCode(@AppUserHeaders AppUserHeaderDetail appUserHeaderDetail,
                                              @RequestBody UserCouponCreateRequest.ByCode request) {
        Long appUserId = appUserHeaderDetail.getAppUserId();
        UserCouponCreateCommand.ByCode command = new UserCouponCreateCommand.ByCode(
                appUserId,
                request.getPromotionCode()
        );
        return userCouponUseCase.createUserCoupon(command);
    }

    @PostMapping("api/admin/v1/user-coupons")
    public List<UserCoupon> createUserCouponAdmin(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail,
                                                  @RequestBody AdminUserCouponCreateRequest request) throws Exception {
        Long adminUserId = adminUserHeaderDetail.getAdminUserId();
        AdminUserCouponCreateCommand command = new AdminUserCouponCreateCommand(
                adminUserId,
                request.getAppUserIds(),
                request.getCouponId()
        );
        return userCouponUseCase.createUserCouponsAdmin(command);
    }

    @DeleteMapping("api/admin/v1/user-coupons/{userCouponId}")
    public Long deleteUserCoupon(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail,
                                 @PathVariable Long userCouponId) throws Exception {
        Long adminUserId = adminUserHeaderDetail.getAdminUserId();
        AdminUserCouponDeleteCommand command = new AdminUserCouponDeleteCommand(
                adminUserId,
                userCouponId
        );
        return userCouponUseCase.deleteUserCoupon(command);
    }
}
