package com.hatefulbug.payment.api.repository;

import com.hatefulbug.payment.api.enums.InvoiceStatus;
import com.hatefulbug.payment.api.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    List<Invoice> getInvoicesByUserId(int userId);

    List<Invoice> findAllByInvoiceStatus(InvoiceStatus invoiceStatus);

    List<Invoice> findAllByInvoiceDateBetween(Instant invoiceDateAfter, Instant invoiceDateBefore);
}
