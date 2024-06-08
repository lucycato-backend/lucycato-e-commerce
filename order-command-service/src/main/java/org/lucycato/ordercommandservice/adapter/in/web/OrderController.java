package org.lucycato.ordercommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    @PostMapping("api/app/v1/orders")
    public void createOrder() {
    }

    @PostMapping("api/app/v1/orders/by-code/{orderCode}/refund")
    public void refund(@PathVariable String orderCode) {

    }

    @PostMapping("api/app/v1/orders/by-code/{orderCode}/order-items/{orderItemId}/refund")
    public void itemRefund(@PathVariable String orderCode,
                       @PathVariable Long orderItemId) {

    }

    @PostMapping("api/admin/v1/orders/{orderId}/refund")
    public void refund(@PathVariable Long orderId) {

    }

    @PostMapping("api/admin/v1/orders/{orderId}/order-items/{orderItemId}/refund")
    public void refund(@PathVariable Long orderId,
                       @PathVariable Long orderItemId) {

    }
}
