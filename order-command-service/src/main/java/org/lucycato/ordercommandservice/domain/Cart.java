package org.lucycato.ordercommandservice.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
public class Cart {
    private Long cartId;
    private Long userId;
    private Long courseId;
    private List<Long> couponIds;
    private List<Long> userCouponIds;
    private BigDecimal payPrice;
    private BigDecimal regularPrice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
