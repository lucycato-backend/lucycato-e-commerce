package org.lucycato.ordercommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;


@Getter
@NoArgsConstructor
public class AdminUserCouponCreateRequest {
    private List<Long> appUserIds;
    private Long couponId;
}
