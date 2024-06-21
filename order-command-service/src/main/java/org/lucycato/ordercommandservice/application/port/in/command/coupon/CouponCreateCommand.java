package org.lucycato.ordercommandservice.application.port.in.command.coupon;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.lucycato.common.SelfValidating;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.component.CouponComponentCommand;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
public class CouponCreateCommand extends SelfValidating<CouponCreateCommand> {
    @NotNull
    private Long adminUserId;
    @NotNull
    private String name;
    private String promotionCode;
    @NotNull
    private LocalDateTime discountStartAt;
    @NotNull
    private LocalDateTime discountEndAt;
    @NotNull
    private List<CouponComponentCommand> components;

    public CouponCreateCommand(Long adminUserId, String name, String promotionCode, LocalDateTime discountStartAt, LocalDateTime discountEndAt, List<CouponComponentCommand> components) {
        this.adminUserId = adminUserId;
        this.name = name;
        this.promotionCode = promotionCode;
        this.discountStartAt = discountStartAt;
        this.discountEndAt = discountEndAt;
        this.components = components;
        validateSelf();
    }
}
