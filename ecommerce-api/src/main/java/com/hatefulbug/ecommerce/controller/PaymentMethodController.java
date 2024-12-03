package com.hatefulbug.ecommerce.controller;

import com.hatefulbug.ecommerce.client.IPaymentMethodClient;
import com.hatefulbug.ecommerce.response.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/payment-methods")
@Tag(name = "PaymentMethod")
public class PaymentMethodController {
    private final IPaymentMethodClient paymentMethodClient;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getPaymentMethods() {
        ApiResponse response = paymentMethodClient.getPaymentMethods();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
