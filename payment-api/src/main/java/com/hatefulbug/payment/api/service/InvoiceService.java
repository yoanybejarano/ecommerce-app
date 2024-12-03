package com.hatefulbug.payment.api.service;

import com.hatefulbug.payment.api.model.Invoice;
import com.hatefulbug.payment.api.request.PartialInvoice;
import com.hatefulbug.payment.api.request.PartialInvoiceUpdate;
import com.hatefulbug.payment.api.request.RangeDateRequest;

import java.util.Date;
import java.util.List;

public interface InvoiceService {

    List<Invoice> getUserInvoices(int userId);

    List<Invoice> getInvoicesByStatus(String status);

    List<Invoice> getInvoicesByDates(RangeDateRequest rangeDate);

    Invoice getInvoice(int invoiceId);

    Invoice createInvoice(PartialInvoice partialInvoice);

    Invoice updateInvoice(PartialInvoiceUpdate partialInvoiceUpdate);

    Invoice updateInvoiceStatus(int invoiceId, String status);

}
