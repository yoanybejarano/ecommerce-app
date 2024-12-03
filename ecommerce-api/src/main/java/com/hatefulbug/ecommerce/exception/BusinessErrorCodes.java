package com.hatefulbug.ecommerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

public enum BusinessErrorCodes {
    NO_CODE(0, NOT_IMPLEMENTED, "No code"),
    PRODUCT_ALREADY_EXISTS(409, CONFLICT, "Product already exists"),
    PRODUCT_NOT_FOUND(404, NOT_FOUND, "Product not found"),
    RESOURCE_NOT_FOUND(404, NOT_FOUND, "Resource not found"),
    BAD_CREDENTIALS(304, FORBIDDEN, "Email and/or Password is incorrect"),
    INVALID_COUPON(400, BAD_REQUEST, "Invalid Coupon"),
    INVALID_EXPIRED_TOKEN(401, UNAUTHORIZED, "Invalid or expired token, you may login and try again"),
    IMAGE_UPDATE_FAILED(500, INTERNAL_SERVER_ERROR, "Upload failed"),
    IMAGE_DELETE_FAILED(500, INTERNAL_SERVER_ERROR, "Delete failed"),
    ;

    @Getter
    private final int code;
    @Getter
    private final String description;
    @Getter
    private final HttpStatus httpStatus;

    BusinessErrorCodes(int code, HttpStatus status, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = status;
    }
}