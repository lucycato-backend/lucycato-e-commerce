package org.lucycato.ordercommandservice.domain.coupon;

import org.lucycato.ordercommandservice.domain.Order;
import org.lucycato.ordercommandservice.domain.OrderItem;

import java.math.BigDecimal;

import java.math.BigDecimal;

public class DiscountFixedComponent implements CouponComponent {
    private String type = "DiscountFixedComponent";
    private BigDecimal discountAmount;

    public DiscountFixedComponent(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
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
        return item.getRegularPrice().subtract(discountAmount);
    }
}

