package org.lucycato.ordercommandservice.application.port.in.command.coupon;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.lucycato.common.SelfValidating;

@Getter
public class AdminUserCouponDeleteCommand extends SelfValidating<AdminUserCouponDeleteCommand>{
    @NotNull
    private Long adminUserId;
    @NotNull
    private Long userCouponId;

    public AdminUserCouponDeleteCommand(Long adminUserId, Long userCouponId) {
        this.adminUserId = adminUserId;
        this.userCouponId = userCouponId;
        validateSelf();
    }
}
