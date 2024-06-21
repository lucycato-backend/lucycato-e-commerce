package org.lucycato.ordercommandservice.application.port.in.command.coupon;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.lucycato.common.SelfValidating;

public class UserCouponCreateCommand {
    @Getter
    public static class ById extends SelfValidating<UserCouponCreateCommand.ById> {
        @NotNull
        private Long appUserId;
        @NotNull
        private Long couponId;

        public ById(Long appUserId, Long couponId) {
            this.appUserId = appUserId;
            this.couponId = couponId;
            validateSelf();
        }
    }

    @Getter
    public static class ByCode extends SelfValidating<UserCouponCreateCommand.ByCode> {
        @NotNull
        private Long appUserId;
        @NotNull
        private String promotionCode;

        public ByCode(Long appUserId, String promotionCode) {
            this.appUserId = appUserId;
            this.promotionCode = promotionCode;
            validateSelf();
        }
    }
}
