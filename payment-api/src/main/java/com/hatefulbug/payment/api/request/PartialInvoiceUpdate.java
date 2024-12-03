package com.hatefulbug.payment.api.request;

import com.hatefulbug.payment.api.enums.InvoiceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class PartialInvoiceUpdate {
    private int invoiceId;
    private BigDecimal amount;
    private Date dueDate;
    private String status;
}
