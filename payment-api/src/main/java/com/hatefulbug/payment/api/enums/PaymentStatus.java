package com.hatefulbug.payment.api.enums;

public enum PaymentStatus {
    Pending("Pending"),
    Completed("Completed"),
    Failed("Failed");

    private String status = "";
    PaymentStatus(String value) {
        this.status = value;
    }

    @Override
    public String toString() {
        return status;
    }
}
