package com.hatefulbug.payment.api.service;

import com.hatefulbug.payment.api.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethod> getPaymentMethods();
    PaymentMethod getPaymentMethod(int id);
}
