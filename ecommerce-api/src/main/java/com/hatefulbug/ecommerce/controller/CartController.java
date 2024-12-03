package com.hatefulbug.ecommerce.controller;

import com.hatefulbug.ecommerce.model.Cart;
import com.hatefulbug.ecommerce.response.ApiResponse;
import com.hatefulbug.ecommerce.service.cart.ICartService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RequiredArgsConstructor
@RestController
@RequestMapping("/carts")
@Tag(name = "Cart")
public class CartController {
    private final ICartService cartService;

    @GetMapping("/my-cart/{cartId}")
    public ResponseEntity<ApiResponse> getCart( @PathVariable Long cartId) {
        Cart cart = cartService.getCart(cartId);
        return ResponseEntity.ok(new ApiResponse("Success", cart));
    }

    @GetMapping("/total-price/{cartId}")
    public ResponseEntity<ApiResponse> getTotalAmount( @PathVariable Long cartId) {
        BigDecimal totalPrice = cartService.getTotalPrice(cartId);
        return ResponseEntity.ok(new ApiResponse("Total Price", totalPrice));
    }

    @DeleteMapping("/clear/{cartId}")
    public ResponseEntity<ApiResponse> clearCart( @PathVariable Long cartId) {
        cartService.clearCart(cartId);
        return ResponseEntity.ok(new ApiResponse("Clear Cart Success!", null));
    }

}