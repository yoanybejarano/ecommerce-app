package com.hatefulbug.payment.api.service;

import com.hatefulbug.payment.api.model.Payment;
import com.hatefulbug.payment.api.request.PartialPayment;
import com.hatefulbug.payment.api.request.RangeDateRequest;

import java.util.List;

public interface PaymentService {
    Payment createPayment(PartialPayment partialPayment);
    Payment updatePaymentStatus(int id, String status);
    Payment getPaymentById(int paymentId);
    List<Payment> getPaymentsByUser(int userId);
    List<Payment> getPaymentsByStatus(String status);
    List<Payment> getPaymentsByDates(RangeDateRequest rangeDate);
}
