package org.lucycato.orderqueryservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CartController {
    @GetMapping("api/app/v1/carts")
    public void getCarts() {

    }
}
