package com.hatefulbug.payment.api.controller;

import com.hatefulbug.payment.api.model.Refund;
import com.hatefulbug.payment.api.reponse.ApiResponse;
import com.hatefulbug.payment.api.request.PartialRefund;
import com.hatefulbug.payment.api.service.RefundService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/refunds")
@RequiredArgsConstructor
public class RefundController {
    private final RefundService refundService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getRefundsByUser(@PathVariable int userId) {
        List<Refund> refunds = refundService.getAllRefundsByUser(userId);
        return new ResponseEntity<>(new ApiResponse(refunds, "Refunds obtained successfully"), HttpStatus.OK);
    }

    @GetMapping("/id/{refundId}")
    public ResponseEntity<ApiResponse> getRefundById(@PathVariable int refundId) {
        Refund refund = refundService.getRefund(refundId);
        return new ResponseEntity<>(new ApiResponse(refund, "Refund obtained successfully"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createRefund(@RequestBody PartialRefund refund) {
        Refund createdRefund = refundService.createRefund(refund);
        return new ResponseEntity<>(new ApiResponse(createdRefund, "Refund created successfully"), HttpStatus.CREATED);
    }

    @PutMapping("/update/id/{refundId}/status/{status}")
    public ResponseEntity<ApiResponse> updateRefundStatus(@PathVariable int refundId, @PathVariable String status) {
        Refund createdRefund = refundService.updateRefundStatus(refundId, status);
        return new ResponseEntity<>(new ApiResponse(createdRefund, "Refund status updated successfully"), HttpStatus.OK);
    }

}
