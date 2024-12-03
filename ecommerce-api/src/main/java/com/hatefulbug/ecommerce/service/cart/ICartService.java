package com.hatefulbug.ecommerce.service.cart;

import com.hatefulbug.ecommerce.model.Cart;
import com.hatefulbug.ecommerce.model.User;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);
    void clearCart(Long id);
    BigDecimal getTotalPrice(Long id);
    Cart initializeNewCart(User user);
    Cart getCartByUserId(Long userId);
}