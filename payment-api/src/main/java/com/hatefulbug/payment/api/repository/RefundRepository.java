package com.hatefulbug.payment.api.repository;

import com.hatefulbug.payment.api.model.Refund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RefundRepository extends JpaRepository<Refund, Integer> {

    @Query(value = "SELECT rf.* FROM refunds rf LEFT JOIN payments pm ON rf.PaymentID = pm.PaymentID AND pm.UserID = ?1", nativeQuery = true)
    List<Refund> findAllByUserId(int userId);
}
