package com.hatefulbug.ecommerce.controller;

import com.hatefulbug.ecommerce.model.Cart;
import com.hatefulbug.ecommerce.model.User;
import com.hatefulbug.ecommerce.response.ApiResponse;
import com.hatefulbug.ecommerce.service.cart.ICartItemService;
import com.hatefulbug.ecommerce.service.cart.ICartService;
import com.hatefulbug.ecommerce.service.user.IUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/cartItems")
@Tag(name = "CartItem")
public class CartItemController {

    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(
            @RequestParam Long productId,
            @RequestParam Integer quantity) {
        User user = userService.getAuthenticatedUser();
        Cart cart= cartService.initializeNewCart(user);
        cartItemService.addItemToCart(cart.getId(), productId, quantity);
        return ResponseEntity.ok(new ApiResponse("Add Item Success", null));
    }

    @PutMapping("/cart/{cartId}/item/{itemId}/update")
    public  ResponseEntity<ApiResponse> updateItemQuantity(@PathVariable Long cartId,
                                                           @PathVariable Long itemId,
                                                           @RequestParam Integer quantity) {
        cartItemService.updateItemQuantity(cartId, itemId, quantity);
        return ResponseEntity.ok(new ApiResponse("Update Item Success", null));
    }

    @DeleteMapping("/cart/{cartId}/item/{itemId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@PathVariable Long cartId, @PathVariable Long itemId) {
        cartItemService.removeItemFromCart(cartId, itemId);
        return ResponseEntity.ok(new ApiResponse("Remove Item Success", null));
    }

}