package com.hatefulbug.payment.api.service;

import com.hatefulbug.payment.api.model.AuditLog;
import com.hatefulbug.payment.api.request.PartialAuditLog;
import com.hatefulbug.payment.api.request.RangeDateRequest;

import java.util.List;

public interface AuditLogService {
    void createAuditLog(PartialAuditLog auditLogData);
    List<AuditLog> getLogsByDateRange (RangeDateRequest rangeDate);
    List<AuditLog> getLogsByUser(int userId);
}
