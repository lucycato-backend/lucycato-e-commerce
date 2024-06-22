package org.lucycato.ordercommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.lucycato.common.annotation.resolver.AdminUserHeaders;
import org.lucycato.common.error.ErrorCodeImpl;
import org.lucycato.common.exception.ApiExceptionImpl;
import org.lucycato.common.resolver.AdminUserHeaderDetail;
import org.lucycato.ordercommandservice.adapter.in.web.request.CouponCreateRequest;
import org.lucycato.ordercommandservice.adapter.in.web.request.CouponUpdateRequest;
import org.lucycato.ordercommandservice.application.port.in.CouponUseCase;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.CouponCreateCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.CouponUpdateCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.component.CategoryComponentCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.component.CouponComponentCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.component.DiscountFixedComponentCommand;
import org.lucycato.ordercommandservice.application.port.in.command.coupon.component.DiscountRateComponentCommand;
import org.lucycato.ordercommandservice.domain.coupon.Coupon;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CouponController {
    private final CouponUseCase couponUseCase;

    @PostMapping("api/admin/v1/coupons")
    public Coupon createCoupon(@AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail,
                               @RequestBody CouponCreateRequest request) throws Exception {
        Long adminUserId = adminUserHeaderDetail.getAdminUserId();

        List<CouponComponentCommand> componentCommands = request.getComponents().stream().map(componentRequest -> {
            String type = (String) componentRequest.get("type");

            return switch (type) {
                case "CategoryComponent" -> {
                    List<String> categories = (List<String>) componentRequest.get("categories");
                    yield new CategoryComponentCommand(categories);
                }
                case "DiscountFixedComponent" -> {
                    BigDecimal discountAmount = new BigDecimal(componentRequest.get("discountAmount").toString());
                    yield new DiscountFixedComponentCommand(discountAmount);
                }
                case "DiscountRateComponent" -> {
                    BigDecimal discountRate = new BigDecimal(componentRequest.get("discountRate").toString());
                    yield new DiscountRateComponentCommand(discountRate);
                }
                default -> throw new ApiExceptionImpl(ErrorCodeImpl.BAD_REQUEST);
            };
        }).toList();

        CouponCreateCommand command = new CouponCreateCommand(
                adminUserId,
                request.getName(),
                request.getPromotionCode(),
                request.getDiscountStartAt(),
                request.getDiscountEndAt(),
                componentCommands
        );

        return couponUseCase.createCoupon(command);
    }

    @PatchMapping("api/admin/v1/coupons/{couponId}")
    public Coupon updateCoupon(@PathVariable Long couponId,
                               @AdminUserHeaders AdminUserHeaderDetail adminUserHeaderDetail,
                               @RequestBody CouponUpdateRequest request) throws Exception {
        Long adminUserId = adminUserHeaderDetail.getAdminUserId();

        List<CouponComponentCommand> componentCommands = request.getComponents().stream().map(componentRequest -> {
            String type = (String) componentRequest.get("type");

            return switch (type) {
                case "CategoryComponent" -> {
                    List<String> categories = (List<String>) componentRequest.get("categories");
                    yield new CategoryComponentCommand(categories);
                }
                case "DiscountFixedComponent" -> {
                    BigDecimal discountAmount = new BigDecimal(componentRequest.get("discountAmount").toString());
                    yield new DiscountFixedComponentCommand(discountAmount);
                }
                case "DiscountRateComponent" -> {
                    BigDecimal discountRate = new BigDecimal(componentRequest.get("discountRate").toString());
                    yield new DiscountRateComponentCommand(discountRate);
                }
                default -> throw new ApiExceptionImpl(ErrorCodeImpl.BAD_REQUEST);
            };
        }).toList();

        CouponUpdateCommand command = new CouponUpdateCommand(
                couponId,
                adminUserId,
                request.getName(),
                request.getPromotionCode(),
                request.getDiscountStartAt(),
                request.getDiscountEndAt(),
                componentCommands
        );

        return couponUseCase.updateCoupon(command);
    }
}
