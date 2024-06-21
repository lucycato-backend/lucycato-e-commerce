package org.lucycato.ordercommandservice.domain.coupon;

import org.lucycato.ordercommandservice.domain.Order;
import org.lucycato.ordercommandservice.domain.OrderItem;

import java.math.BigDecimal;

public interface CouponComponent {
    String getType();
    boolean isApplicable(OrderItem item);
    BigDecimal applyDiscount(OrderItem item);
}
