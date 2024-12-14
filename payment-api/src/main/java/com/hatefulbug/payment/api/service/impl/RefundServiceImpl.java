package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.enums.RefundStatus;
import com.hatefulbug.payment.api.model.Payment;
import com.hatefulbug.payment.api.model.Refund;
import com.hatefulbug.payment.api.repository.RefundRepository;
import com.hatefulbug.payment.api.request.PartialRefund;
import com.hatefulbug.payment.api.service.PaymentService;
import com.hatefulbug.payment.api.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    private final PaymentService paymentService;

    @Transactional
    @Override
    public Refund createRefund(PartialRefund partialRefund) {
        try {
            Payment payment = paymentService.getPaymentById(partialRefund.getPaymentID());
            if (payment != null) {
                Refund newRefund = new Refund();
                newRefund.setPayment(payment);
                newRefund.setRefundAmount(partialRefund.getRefundAmount());
                newRefund.setRefundReason(partialRefund.getRefundReason());
                newRefund.setRefundStatus(RefundStatus.valueOf(partialRefund.getRefundStatus()));
                newRefund.setRefundDate(Instant.now());
                return refundRepository.save(newRefund);
            }
            return null;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Refund getRefund(int refundId) {
        return refundRepository.findById(refundId).orElseThrow(() -> new RuntimeException(String.format("Refund with %s not found.", refundId)));
    }

    @Transactional
    @Override
    public Refund updateRefundStatus(int refundId, String refundStatus) {
        try {
            Refund refund = getRefund(refundId);
            if (refund != null) {
                refund.setRefundStatus(RefundStatus.valueOf(refundStatus));
                return refundRepository.save(refund);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Refund> getAllRefundsByUser(int userId) {
        return refundRepository.findAllByUserId(userId);
    }
}
