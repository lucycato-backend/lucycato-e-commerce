package org.lucycato.ordercommandservice.adapter.in.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CartController {
    @PostMapping("api/app/v1/carts")
    public void createCart() {

    }

    @PatchMapping("api/app/v1/carts/{cartId}")
    public void updateCart(@PathVariable Long cartId) {

    }
    @DeleteMapping("api/app/v1/carts")
    public void deleteCarts() {

    }
    @DeleteMapping("api/app/v1/carts/{cartId}")
    public void deleteCart(@PathVariable Long cartId) {

    }
}
