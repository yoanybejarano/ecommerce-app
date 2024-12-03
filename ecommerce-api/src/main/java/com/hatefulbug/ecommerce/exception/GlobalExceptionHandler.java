package com.hatefulbug.ecommerce.exception;

import io.jsonwebtoken.JwtException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashSet;
import java.util.Set;

import static com.hatefulbug.ecommerce.exception.BusinessErrorCodes.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(ProductNotFoundException exp) {
        return ResponseEntity
                .status(CONFLICT)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(PRODUCT_NOT_FOUND.getCode())
                                .businessErrorDescription(PRODUCT_NOT_FOUND.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(ResourceNotFoundException exp) {
        return ResponseEntity
                .status(CONFLICT)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(RESOURCE_NOT_FOUND.getCode())
                                .businessErrorDescription(RESOURCE_NOT_FOUND.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleException(AlreadyExistsException exp) {
        return ResponseEntity
                .status(CONFLICT)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(PRODUCT_ALREADY_EXISTS.getCode())
                                .businessErrorDescription(PRODUCT_ALREADY_EXISTS.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    /*@ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ExceptionResponse> handleException(AuthenticationException exp) {
        return ResponseEntity
                .status(BAD_CREDENTIALS.getCode())
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleException() {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(BAD_CREDENTIALS.getCode())
                                .businessErrorDescription(BAD_CREDENTIALS.getDescription())
                                .error("Login and / or Password is incorrect")
                                .build()
                );
    }*/

    @ExceptionHandler(ImageOperationException.class)
    public ResponseEntity<ExceptionResponse> handleException(ImageOperationException exp) {
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(IMAGE_UPDATE_FAILED.getCode())
                                .businessErrorDescription(IMAGE_UPDATE_FAILED.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(JwtException.class)
    public ResponseEntity<ExceptionResponse> handleException(JwtException exp) {
        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorCode(INVALID_EXPIRED_TOKEN.getCode())
                                .businessErrorDescription(INVALID_EXPIRED_TOKEN.getDescription())
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<ExceptionResponse> handleException(OperationNotPermittedException exp) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .error(exp.getMessage())
                                .build()
                );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        Set<String> errors = new HashSet<>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    //var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.add(errorMessage);
                });
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(
                        ExceptionResponse.builder()
                                .validationErrors(errors)
                                .build()
                );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception exp) {
        exp.printStackTrace();
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(
                        ExceptionResponse.builder()
                                .businessErrorDescription("Internal error, please contact the admin")
                                .error(exp.getMessage())
                                .build()
                );
    }
}
