package org.lucycato.ordercommandservice.domain;

import lombok.Getter;
import org.lucycato.ordercommandservice.domain.enums.OrderItemStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderItem {
    private Long orderItemId;
    private OrderItemStatus status;
    private BigDecimal payPrice;
    private BigDecimal regularPrice;
    private String courseCategory;
    private Long courseId;
    private Long orderId;
    private List<Long> couponIds;
    private List<Long> userCouponIds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
