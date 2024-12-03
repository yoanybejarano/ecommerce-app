package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.model.PaymentMethod;
import com.hatefulbug.payment.api.repository.PaymentMethodRepository;
import com.hatefulbug.payment.api.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {
    private final PaymentMethodRepository paymentMethodRepository;

    @Override
    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @Override
    public PaymentMethod getPaymentMethod(int id) {
        return paymentMethodRepository.findById(id).orElseThrow(() -> new RuntimeException(String.format("Payment Method with id %s not found", id)));
    }
}
