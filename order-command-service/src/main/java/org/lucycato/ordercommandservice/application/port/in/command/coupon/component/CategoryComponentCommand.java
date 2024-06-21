package org.lucycato.ordercommandservice.application.port.in.command.coupon.component;

import org.lucycato.ordercommandservice.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;


public class CategoryComponentCommand implements CouponComponentCommand {
    private String type = "CategoryComponent";
    private List<String> categories;

    public CategoryComponentCommand(List<String> categories) {
        this.categories = categories;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public boolean isApplicable(OrderItem item) {
        return categories.contains(item.getCourseCategory());
    }

    @Override
    public BigDecimal applyDiscount(OrderItem item) {
        return item.getPayPrice(); // No discount applied, just a placeholder
    }
}

