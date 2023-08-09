package com.payment.proportionalpaymentcalculator.services;


import com.payment.proportionalpaymentcalculator.dto.PaymentDTO;
import com.payment.proportionalpaymentcalculator.entities.Order;
import com.payment.proportionalpaymentcalculator.filters.OrderFilter;
import com.payment.proportionalpaymentcalculator.rest.PaymentRequest;
import com.payment.proportionalpaymentcalculator.rest.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImp implements PaymentService {

    @Autowired
    private List<OrderFilter> orderFilter;

    @Override
    public PaymentResponse calculatePayment(PaymentRequest request) {
        List<PaymentDTO> paymentsLink = new ArrayList<>();

        for (Order order : request.getOrders()) {
            applyFiltersToOrder(request, order);

            PaymentDTO proportionalPayment = createPaymentDTO(order);
            paymentsLink.add(proportionalPayment);
        }

        return new PaymentResponse(paymentsLink);
    }

    private void applyFiltersToOrder(PaymentRequest request, Order order) {
        for (OrderFilter filter : orderFilter) {
            filter.paymentFilter(request, order);
        }
    }

    private PaymentDTO createPaymentDTO(Order order) {
        PaymentDTO proportionalPayment = new PaymentDTO();
        proportionalPayment.setNameOfFriend(order.getNameOfFriend());
        proportionalPayment.setValueToPay(order.getOrderAmount());
        return proportionalPayment;
    }
}
