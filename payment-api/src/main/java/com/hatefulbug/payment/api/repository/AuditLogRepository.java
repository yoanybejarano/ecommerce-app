package com.hatefulbug.payment.api.repository;

import com.hatefulbug.payment.api.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AuditLogRepository extends JpaRepository<AuditLog, Integer> {
    List<AuditLog> findAllByUserId(int userId);
    List<AuditLog> findAllByTimestampBetween(Instant timestampAfter, Instant timestampBefore);
}
