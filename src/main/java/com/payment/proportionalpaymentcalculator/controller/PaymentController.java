package com.payment.proportionalpaymentcalculator.controller;


import com.payment.proportionalpaymentcalculator.rest.PaymentRequest;
import com.payment.proportionalpaymentcalculator.rest.PaymentResponse;
import com.payment.proportionalpaymentcalculator.services.PaymentServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentServiceImp paymentService;

    @PostMapping(value = "/calculate-price", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PaymentResponse> calculatePayment(@RequestBody PaymentRequest paymentRequest) {
        return ResponseEntity.ok(paymentService.calculatePayment(paymentRequest));
    }
}
