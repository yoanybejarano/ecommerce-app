package com.hatefulbug.ecommerce.service.order;

import com.hatefulbug.ecommerce.dto.OrderDto;
import com.hatefulbug.ecommerce.model.Order;

import java.util.List;

public interface IOrderService {
    Order placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
    OrderDto convertToDto(Order order);
}