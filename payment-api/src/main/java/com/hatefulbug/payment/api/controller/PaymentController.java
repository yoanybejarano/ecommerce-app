package com.hatefulbug.payment.api.controller;

import com.hatefulbug.payment.api.model.Payment;
import com.hatefulbug.payment.api.reponse.ApiResponse;
import com.hatefulbug.payment.api.request.PartialPayment;
import com.hatefulbug.payment.api.request.RangeDateRequest;
import com.hatefulbug.payment.api.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getPaymentById(@PathVariable int id) {
        Payment payment = paymentService.getPaymentById(id);
        return new ResponseEntity<>(new ApiResponse(payment, "Payment successfully obtained"), HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse> getPaymentsByStatus(@PathVariable String status) {
        List<Payment> payments = paymentService.getPaymentsByStatus(status);
        return new ResponseEntity<>(new ApiResponse(payments, "Payments successfully obtained"), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getPaymentsByUser(@PathVariable int userId) {
        List<Payment> payments = paymentService.getPaymentsByUser(userId);
        return new ResponseEntity<>(new ApiResponse(payments, "Payments successfully obtained"), HttpStatus.OK);
    }

    @GetMapping("/byRangeDate")
    public ResponseEntity<ApiResponse> getPaymentsByDates(@RequestBody RangeDateRequest rangeDate) {
        List<Payment> payments = paymentService.getPaymentsByDates(rangeDate);
        return new ResponseEntity<>(new ApiResponse(payments, "Payments successfully obtained"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createPayment(@RequestBody PartialPayment data) {
        Payment payment = paymentService.createPayment(data) ;
        return new ResponseEntity<>(new ApiResponse(payment, "Payment successfully created"), HttpStatus.CREATED);
    }

    @PutMapping("/update/{paymentId}/status/{status}")
    public ResponseEntity<ApiResponse> updatePaymentStatus(@PathVariable int paymentId,@PathVariable String status) {
        Payment payment = paymentService.updatePaymentStatus(paymentId, status) ;
        return new ResponseEntity<>(new ApiResponse(payment, "Payment status successfully updated"), HttpStatus.OK);
    }

}
