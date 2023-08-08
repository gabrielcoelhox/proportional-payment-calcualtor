package com.payment.proportionalpaymentcalculator.services;


import com.payment.proportionalpaymentcalculator.rest.PaymentRequest;
import com.payment.proportionalpaymentcalculator.rest.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {
    @Override
    public PaymentResponse calculatePayment(PaymentRequest request) {
        return null;
    }
}
