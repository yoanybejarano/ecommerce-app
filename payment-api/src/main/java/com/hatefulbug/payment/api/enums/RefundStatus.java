package com.hatefulbug.payment.api.enums;

public enum RefundStatus {
    Initiated("Initiated"),
    Processed("Processed");

    private String status;
    RefundStatus(String status) {
        this.status = status;
    }
}
