package org.lucycato.ordercommandservice.application.port.in.command.coupon.component;

import org.lucycato.ordercommandservice.domain.OrderItem;

import java.math.BigDecimal;

public class DiscountFixedComponentCommand implements CouponComponentCommand {
    private String type = "DiscountFixedComponent";
    private BigDecimal discountAmount;

    public DiscountFixedComponentCommand(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isApplicable(OrderItem item) {
        return true;
    }

    @Override
    public BigDecimal applyDiscount(OrderItem item) {
        return item.getRegularPrice().subtract(discountAmount);
    }
}

