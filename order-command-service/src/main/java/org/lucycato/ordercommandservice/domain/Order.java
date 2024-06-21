package org.lucycato.ordercommandservice.domain;

import lombok.Getter;
import org.lucycato.ordercommandservice.domain.enums.OrderStatus;

import java.math.BigDecimal;
import java.util.List;

@Getter
public class Order {
    private Long orderId;
    private String code;
    private OrderStatus status;
    private BigDecimal point;
    private BigDecimal payPrice;
    private BigDecimal regularPrice;
    private Long userId;
    private List<OrderItem> orderItems;
}
