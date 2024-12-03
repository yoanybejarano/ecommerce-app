package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.enums.CurrencyType;
import com.hatefulbug.payment.api.enums.PaymentStatus;
import com.hatefulbug.payment.api.model.Payment;
import com.hatefulbug.payment.api.model.PaymentMethod;
import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.repository.PaymentRepository;
import com.hatefulbug.payment.api.request.PartialAuditLog;
import com.hatefulbug.payment.api.request.PartialPayment;
import com.hatefulbug.payment.api.request.RangeDateRequest;
import com.hatefulbug.payment.api.service.AuditLogService;
import com.hatefulbug.payment.api.service.PaymentMethodService;
import com.hatefulbug.payment.api.service.PaymentService;
import com.hatefulbug.payment.api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final UserService userService;
    private final PaymentMethodService paymentMethodService;
    private final AuditLogService logService;

    @Transactional
    @Override
    public Payment createPayment(PartialPayment partialPayment) {
        Payment payment = new Payment();
        try {
            User user = userService.getUserById(partialPayment.getUserId());
            PaymentMethod paymentMethod = paymentMethodService.getPaymentMethod(partialPayment.getPaymentMethodId());
            String transactionId = UUID.randomUUID().toString().replace("-", "").toUpperCase();
            if (user != null && paymentMethod != null) {
                payment.setUser(user);
                payment.setAmount(partialPayment.getAmount());
                payment.setCurrency(CurrencyType.valueOf(partialPayment.getCurrency()));
                payment.setPaymentMethod(paymentMethod);
                payment.setPaymentStatus(PaymentStatus.Pending);
                payment.setPaymentDate(Instant.now());
                payment.setTransactionID(transactionId);
                Payment result = paymentRepository.save(payment);
                logService.createAuditLog(PartialAuditLog.builder()
                        .userId(result.getUser().getId())
                        .action("Transaction Created")
                        .details(String.format("Transaction %s created successfully.", transactionId)).build());
                return result;
            }
            return null;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Payment updatePaymentStatus(int id, String status) {
        try {
            Payment payment = getPaymentById(id);
            if (payment != null && !Objects.equals(payment.getPaymentStatus().toString(), status)) {
                payment.setPaymentStatus(PaymentStatus.valueOf(status));
                Payment result = paymentRepository.save(payment);
                logService.createAuditLog(PartialAuditLog.builder()
                        .userId(result.getUser().getId())
                        .action("Transaction Status Updated")
                        .details(String.format("Transaction %s status updated successfully.", result.getTransactionID())).build());
                return result;
            }
            return null;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Payment getPaymentById(int paymentId) {
        return paymentRepository.findById(paymentId).orElseThrow(() -> new RuntimeException(String.format("Payment with id %s not found.", paymentId)));
    }

    @Override
    public List<Payment> getPaymentsByUser(int userId) {
        return paymentRepository.findAllByUserId(userId);
    }

    @Override
    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findAllByPaymentStatus(PaymentStatus.valueOf(status));
    }

    @Override
    public List<Payment> getPaymentsByDates(RangeDateRequest rangeDate) {
        return paymentRepository.findAllByPaymentDateBetween(rangeDate.getStartDate().toInstant(), rangeDate.getEndDate().toInstant());
    }
}
