package com.hatefulbug.ecommerce.controller;

import com.hatefulbug.ecommerce.dto.OrderDto;
import com.hatefulbug.ecommerce.model.Order;
import com.hatefulbug.ecommerce.response.ApiResponse;
import com.hatefulbug.ecommerce.service.order.IOrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
@Tag(name = "Order")
public class OrderController {

    private final IOrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<ApiResponse> createOrder(@RequestParam Long userId) {
        Order order =  orderService.placeOrder(userId);
        OrderDto orderDto =  orderService.convertToDto(order);
        return ResponseEntity.ok(new ApiResponse("Item Order Success!", orderDto));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long orderId) {
        OrderDto order = orderService.getOrder(orderId);
        return ResponseEntity.ok(new ApiResponse("Item Order Success!", order));
    }

    @GetMapping("/user/{userId}/order")
    public ResponseEntity<ApiResponse> getUserOrders(@PathVariable Long userId) {
        List<OrderDto> order = orderService.getUserOrders(userId);
        return ResponseEntity.ok(new ApiResponse("Item Order Success!", order));
    }
}