package com.hatefulbug.payment.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class PartialRefund {
    private int paymentID;
    private BigDecimal refundAmount;
    private String refundReason;
    private String refundStatus;

}
