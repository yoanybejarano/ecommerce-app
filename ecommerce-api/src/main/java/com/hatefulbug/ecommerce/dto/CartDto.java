package com.hatefulbug.ecommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {
    private Long cartId;
    private Set<CartItemDto> items;
    private BigDecimal totalAmount;
}