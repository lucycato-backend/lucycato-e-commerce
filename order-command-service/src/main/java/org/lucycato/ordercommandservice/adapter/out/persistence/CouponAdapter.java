package org.lucycato.ordercommandservice.adapter.out.persistence;

import org.lucycato.common.annotation.hexagonal.out.PersistenceAdapter;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.component.CouponComponentCommand;
import org.lucycato.ordercommandservice.application.port.out.CouponPort;
import org.lucycato.ordercommandservice.application.port.out.result.CouponResult;

import java.time.LocalDateTime;
import java.util.List;

@PersistenceAdapter
public class CouponAdapter implements CouponPort {
    @Override
    public CouponResult save(String name, String promotionCode, LocalDateTime discountStartAt, LocalDateTime discountEndAt, List<CouponComponentCommand> components) {
        return null;
    }

    @Override
    public CouponResult update(Long couponId, String name, String promotionCode, LocalDateTime discountStartAt, LocalDateTime discountEndAt, List<CouponComponentCommand> components) {
        return null;
    }

    @Override
    public CouponResult findCouponById(Long couponId) {
        return null;
    }

    @Override
    public CouponResult findCouponByCode(String code) {
        return null;
    }
}
