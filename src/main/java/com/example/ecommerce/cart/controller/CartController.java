package com.example.ecommerce.cart.controller;

import com.example.ecommerce.cart.model.request.GetCartInReq;
import com.example.ecommerce.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;

    @RequestMapping(method = RequestMethod.POST, value = "/in")
    public ResponseEntity<Object> cartIn(@RequestBody GetCartInReq getCartInReq, Authentication authentication) {
        cartService.cartIn(getCartInReq, authentication.getName());
        return ResponseEntity.ok().body("ok");
    }

    @RequestMapping(method = RequestMethod.GET, value = "/list")
    public ResponseEntity<Object> cartList(@RequestHeader(value = "Authorization") String token) {
        return ResponseEntity.ok().body(cartService.cartList(token));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/cancel/{idx}")
    public ResponseEntity<Object> cartCancel(@PathVariable Long idx) {
        return ResponseEntity.ok().body(cartService.cartCancel(idx));
    }
}
