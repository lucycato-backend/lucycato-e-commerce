package org.lucycato.ordercommandservice.domain.coupon;

import org.lucycato.ordercommandservice.domain.Order;
import org.lucycato.ordercommandservice.domain.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public class CategoryComponent implements CouponComponent {
    private String type = "CategoryComponent";
    private List<String> categories;

    public CategoryComponent(List<String> categories) {
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
        return item.getRegularPrice(); // No discount applied, just a placeholder
    }
}

