package org.lucycato.orderqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {
    @GetMapping("api/admin/v1/orders")
    public void getOrders() {

    }

    @GetMapping("api/app/v1/orders/by-code/{orderCode}")
    public void getOrder(@PathVariable String orderCode) {

    }

    @GetMapping("api/admin/v1/orders/{orderId}")
    public void getOrder(@PathVariable Long orderId) {
        
    }
}
