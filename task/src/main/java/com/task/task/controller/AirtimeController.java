package com.task.task.controller;


import com.task.task.model.AirtimeApiResponse;
import com.task.task.model.AirtimePaymentRequest;
import com.task.task.services.AirtimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AirtimeController {
    @Autowired
    private AirtimeService airtimeService;

    @PostMapping("/airtime-payment")
    public ResponseEntity<AirtimeApiResponse> makeAirtimePayment(@RequestBody AirtimePaymentRequest paymentRequest) {
        AirtimeApiResponse response = airtimeService.makeAirtimePayment(paymentRequest);
        return ResponseEntity.ok(response);
    }
}

