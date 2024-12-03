package com.hatefulbug.payment.api.repository;

import com.hatefulbug.payment.api.model.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Integer> {
}
