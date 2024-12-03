package com.hatefulbug.payment.api.controller;

import com.hatefulbug.payment.api.model.Invoice;
import com.hatefulbug.payment.api.reponse.ApiResponse;
import com.hatefulbug.payment.api.request.PartialInvoice;
import com.hatefulbug.payment.api.request.PartialInvoiceUpdate;
import com.hatefulbug.payment.api.request.RangeDateRequest;
import com.hatefulbug.payment.api.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/invoices")
@RequiredArgsConstructor
public class InvoiceController {
    private final InvoiceService invoiceService;

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponse> getInvoicesByStatus(@PathVariable  String status) {
        List<Invoice> invoices = invoiceService.getInvoicesByStatus(status);
        return new ResponseEntity<>(new ApiResponse(invoices, "Invoices successfully obtained"), HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getInvoicesByUser(@PathVariable  int userId) {
        List<Invoice> invoices = invoiceService.getUserInvoices(userId) ;
        return new ResponseEntity<>(new ApiResponse(invoices, "Invoices successfully obtained"), HttpStatus.OK);
    }

    @GetMapping("/byRangeDate")
    public ResponseEntity<ApiResponse> getInvoicesByDates(@RequestBody RangeDateRequest rangeDate) {
        List<Invoice> invoices = invoiceService.getInvoicesByDates(rangeDate) ;
        return new ResponseEntity<>(new ApiResponse(invoices, "Invoices successfully obtained"), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getInvoice(@PathVariable int id) {
        Invoice invoice = invoiceService.getInvoice(id) ;
        return new ResponseEntity<>(new ApiResponse(invoice, "Invoice successfully obtained"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createInvoice(@RequestBody PartialInvoice partialInvoice) {
        Invoice invoice = invoiceService.createInvoice(partialInvoice) ;
        return new ResponseEntity<>(new ApiResponse(invoice, "Invoice successfully created"), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateInvoice(@RequestBody PartialInvoiceUpdate data ) {
        Invoice invoice = invoiceService.updateInvoice(data) ;
        return new ResponseEntity<>(new ApiResponse(invoice, "Invoice successfully updated"), HttpStatus.OK);
    }

    @PutMapping("/update/{invoiceId}/status/{status}")
    public ResponseEntity<ApiResponse> updateInvoice(@PathVariable int invoiceId, @PathVariable  String status) {
        Invoice invoice = invoiceService.updateInvoiceStatus(invoiceId, status) ;
        return new ResponseEntity<>(new ApiResponse(invoice, "Invoice status successfully updated"), HttpStatus.OK);
    }




















}
