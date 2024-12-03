package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.enums.InvoiceStatus;
import com.hatefulbug.payment.api.model.Invoice;
import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.repository.InvoiceRepository;
import com.hatefulbug.payment.api.request.PartialAuditLog;
import com.hatefulbug.payment.api.request.PartialInvoice;
import com.hatefulbug.payment.api.request.PartialInvoiceUpdate;
import com.hatefulbug.payment.api.request.RangeDateRequest;
import com.hatefulbug.payment.api.service.AuditLogService;
import com.hatefulbug.payment.api.service.InvoiceService;
import com.hatefulbug.payment.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserService userService;
    private final AuditLogService logService;

    @Override
    public Invoice getInvoice(int invoiceId) {
        return invoiceRepository.findById(invoiceId).orElseThrow(() -> new RuntimeException(String.format("Invoice with id %s not found", invoiceId)));
    }

    @Override
    public List<Invoice> getUserInvoices(int userId) {
        return invoiceRepository.getInvoicesByUserId(userId);
    }

    @Override
    public List<Invoice> getInvoicesByStatus(String status) {
        InvoiceStatus invoiceStatus = InvoiceStatus.valueOf(status);
        return invoiceRepository.findAllByInvoiceStatus(invoiceStatus);
    }

    @Override
    public List<Invoice> getInvoicesByDates(RangeDateRequest rangeDate) {
        return invoiceRepository.findAllByInvoiceDateBetween(rangeDate.getStartDate().toInstant(), rangeDate.getEndDate().toInstant());
    }

    @Transactional
    @Override
    public Invoice createInvoice(PartialInvoice partialInvoice) {
        try {
            User user = userService.getUserById(partialInvoice.getUserId());
            if (user != null) {
                Invoice invoice = new Invoice();
                invoice.setUser(user);
                invoice.setTotalAmount(partialInvoice.getTotalAmount());
                invoice.setInvoiceDate(Instant.now());
                invoice.setDueDate(partialInvoice.getDueDate().toInstant());
                invoice.setInvoiceStatus(InvoiceStatus.Unpaid);
                Invoice result = invoiceRepository.save(invoice);
                logService.createAuditLog(PartialAuditLog.builder()
                        .userId(partialInvoice.getUserId())
                        .action("Invoice Created")
                        .details(String.format("Invoice %s created successfully.", result.getId())).build());
                return result;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Invoice updateInvoice(PartialInvoiceUpdate data) {
        try {
            Invoice invoice = getInvoice(data.getInvoiceId());
            if (invoice != null) {
                invoice.setTotalAmount(data.getAmount());
                invoice.setDueDate(data.getDueDate().toInstant());
                invoice.setInvoiceStatus(InvoiceStatus.valueOf(data.getStatus()));
                Invoice result = invoiceRepository.save(invoice);
                logService.createAuditLog(PartialAuditLog.builder()
                        .userId(invoice.getUser().getId())
                        .action("Invoice Updated")
                        .details(String.format("Invoice %s updated successfully.", invoice.getId()))
                        .build());
                return result;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    @Override
    public Invoice updateInvoiceStatus(int invoiceId, String status) {
        try {
            Invoice invoice = getInvoice(invoiceId);
            if (invoice != null) {
                invoice.setInvoiceStatus(InvoiceStatus.valueOf(status));
                Invoice result = invoiceRepository.save(invoice);
                logService.createAuditLog(PartialAuditLog.builder()
                        .userId(invoice.getUser().getId())
                        .action("Invoice Status Updated")
                        .details(String.format("Invoice %s status changed to %s.", invoice.getId(), invoice.getInvoiceStatus().name()))
                        .build());
                return result;
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
