package com.payment.proportionalpaymentcalculator.filters;

import com.payment.proportionalpaymentcalculator.entities.Order;
import com.payment.proportionalpaymentcalculator.rest.PaymentRequest;

public interface OrderFilter {
    public void paymentFilter(PaymentRequest paymentRequest, Order order);

}
