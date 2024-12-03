package com.hatefulbug.payment.api.repository;

import com.hatefulbug.payment.api.enums.PaymentStatus;
import com.hatefulbug.payment.api.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
    List<Payment> findAllByUserId(int userId);

    List<Payment> findAllByPaymentDateBetween(Instant paymentDateAfter, Instant paymentDateBefore);

    List<Payment> findAllByPaymentStatus(PaymentStatus paymentStatus);
}
