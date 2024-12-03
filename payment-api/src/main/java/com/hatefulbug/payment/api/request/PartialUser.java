package com.hatefulbug.payment.api.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PartialUser {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
