package org.lucycato.ordercommandservice.application.port.in.command.coupon.component;

import org.lucycato.ordercommandservice.domain.OrderItem;

import java.math.BigDecimal;

public class DiscountRateComponentCommand implements CouponComponentCommand {
    private String type = "DiscountRateComponent";
    private BigDecimal discountRate;

    public DiscountRateComponentCommand(BigDecimal discountRate) {
        this.discountRate = discountRate;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isApplicable(OrderItem item) {
        return true; // This discount applies to any item
    }

    @Override
    public BigDecimal applyDiscount(OrderItem item) {
        return item.getRegularPrice().multiply(BigDecimal.ONE.subtract(discountRate));
    }
}
