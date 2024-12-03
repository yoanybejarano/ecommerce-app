package com.hatefulbug.payment.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PartialAuditLog {
    private String action;
    private int userId;
    private String details;
}
