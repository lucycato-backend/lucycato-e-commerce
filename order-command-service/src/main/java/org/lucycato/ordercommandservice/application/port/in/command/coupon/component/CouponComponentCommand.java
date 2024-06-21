package org.lucycato.ordercommandservice.application.port.in.command.coupon.component;

import org.lucycato.ordercommandservice.domain.OrderItem;

import java.math.BigDecimal;

public interface CouponComponentCommand {
    String getType();

    boolean isApplicable(OrderItem item);

    BigDecimal applyDiscount(OrderItem item);

}