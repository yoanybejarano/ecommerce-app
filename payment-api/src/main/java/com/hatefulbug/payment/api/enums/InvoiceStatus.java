package com.hatefulbug.payment.api.enums;

public enum InvoiceStatus {
    Paid("Paid"),
    Unpaid("Unpaid"),
    Overdue("Overdue");

    private String status;
    InvoiceStatus(String status) {
        this.status = status;
    }
}
