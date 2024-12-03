package com.hatefulbug.payment.api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED, "No code"),
    RESOURCE_NOT_FOUND(404, NOT_FOUND, "Resource not found"),
    BAD_CREDENTIALS(403, FORBIDDEN, "Email and/or Password is incorrect"),
    INVALID_TOKEN(401, UNAUTHORIZED, "Invalid token, you may login and try again"),
    EXPIRED_TOKEN(401, UNAUTHORIZED, "Expired token, you may login and try again"),
    SERVER_ERROR(500, INTERNAL_SERVER_ERROR, "Upload failed");

    private final int code;
    private final String description;
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus status, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = status;
    }
}
