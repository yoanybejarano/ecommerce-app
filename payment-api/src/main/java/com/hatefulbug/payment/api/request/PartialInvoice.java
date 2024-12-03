package com.hatefulbug.payment.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
public class PartialInvoice {
    private int userId;
    private BigDecimal totalAmount;
    private Date dueDate;
}
