package com.hatefulbug.payment.api.service;

import com.hatefulbug.payment.api.enums.RefundStatus;
import com.hatefulbug.payment.api.model.Refund;
import com.hatefulbug.payment.api.request.PartialRefund;

import java.util.List;

public interface RefundService {
    Refund createRefund(PartialRefund refund);
    Refund getRefund(int refundId);
    Refund updateRefundStatus(int refundId, String refundStatus);
    List<Refund> getAllRefundsByUser(int userId);
}
