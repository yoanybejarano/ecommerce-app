package com.hatefulbug.payment.api.controller;

import com.hatefulbug.payment.api.model.AuditLog;
import com.hatefulbug.payment.api.reponse.ApiResponse;
import com.hatefulbug.payment.api.request.RangeDateRequest;
import com.hatefulbug.payment.api.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
public class LogController {
    private final AuditLogService logService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<ApiResponse> getLogsByUser(@PathVariable int userId) {
        List<AuditLog> logs = logService.getLogsByUser(userId);
        return new ResponseEntity<>(new ApiResponse(logs, "Logs obtained success"), HttpStatus.OK);
    }

    @GetMapping("/byDateRange")
    public ResponseEntity<ApiResponse> getLogsByDates(@RequestBody RangeDateRequest dateRange) {
        List<AuditLog> logs = logService.getLogsByDateRange(dateRange) ;
        return new ResponseEntity<>(new ApiResponse(logs, "Logs successfully obtained"), HttpStatus.OK);
    }

}
