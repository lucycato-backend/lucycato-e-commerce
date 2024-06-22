package org.lucycato.ordercommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Getter
@NoArgsConstructor
public class CouponUpdateRequest {
    private String name;
    private Boolean status;
    private String promotionCode;
    private LocalDateTime discountStartAt;
    private LocalDateTime discountEndAt;
    private List<Map<String, Object>> components;
}
