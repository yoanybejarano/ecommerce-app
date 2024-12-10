package com.hatefulbug.payment.api.controller;

import com.hatefulbug.payment.api.model.PaymentMethod;
import com.hatefulbug.payment.api.reponse.ApiResponse;
import com.hatefulbug.payment.api.service.PaymentMethodService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/paymentMethods")
@RequiredArgsConstructor
@Tag(name="PaymentMethod")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @GetMapping
    public ResponseEntity<ApiResponse> getPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getPaymentMethods();
        return new ResponseEntity<>(new ApiResponse(paymentMethods, "Payment methods successfully obtained"), HttpStatus.OK);
    }

}
