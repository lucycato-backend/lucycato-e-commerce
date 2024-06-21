package org.lucycato.ordercommandservice.adapter.in.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;


public class UserCouponCreateRequest {

    @Getter
    @NoArgsConstructor
    public static class ById {
        private Long couponId;
    }

    @Getter
    @NoArgsConstructor
    public static class ByCode {
        private String promotionCode;
    }
}
