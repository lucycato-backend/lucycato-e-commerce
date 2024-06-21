package org.lucycato.ordercommandservice.application.port.in.command.coupon;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.lucycato.common.SelfValidating;

import java.util.List;

@Getter
public class AdminUserCouponCreateCommand extends SelfValidating<AdminUserCouponCreateCommand> {
    @NotNull
    private Long adminUserId;
    @NotNull
    private List<Long> appUserIds;
    @NotNull
    private Long couponId;

    public AdminUserCouponCreateCommand(Long adminUserId, List<Long> appUserIds, Long couponId) {
        this.adminUserId = adminUserId;
        this.appUserIds = appUserIds;
        this.couponId = couponId;
        validateSelf();
    }
}
