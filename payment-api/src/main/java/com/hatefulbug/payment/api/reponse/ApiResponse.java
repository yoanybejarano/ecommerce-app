package com.hatefulbug.payment.api.reponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {
    private Object data;
    private String message;
}
