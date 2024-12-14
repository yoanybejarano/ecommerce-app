package com.hatefulbug.payment.api.service.impl;

import com.hatefulbug.payment.api.enums.InvoiceStatus;
import com.hatefulbug.payment.api.model.Invoice;
import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.repository.InvoiceRepository;
import com.hatefulbug.payment.api.request.PartialInvoice;
import com.hatefulbug.payment.api.request.PartialInvoiceUpdate;
import com.hatefulbug.payment.api.request.RangeDateRequest;
import com.hatefulbug.payment.api.service.InvoiceService;
import com.hatefulbug.payment.api.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final UserService userService;

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
                return invoiceRepository.save(invoice);
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
                return invoiceRepository.save(invoice);
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
                return invoiceRepository.save(invoice);
            }
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
