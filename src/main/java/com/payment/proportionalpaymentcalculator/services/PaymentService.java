package com.payment.proportionalpaymentcalculator.services;

import com.payment.proportionalpaymentcalculator.rest.PaymentRequest;
import com.payment.proportionalpaymentcalculator.rest.PaymentResponse;

public interface PaymentService {
    public PaymentResponse calculatePayment(PaymentRequest request);
}
