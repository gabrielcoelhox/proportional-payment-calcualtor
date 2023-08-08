package com.payment.proportionalpaymentcalculator.filters;

import com.payment.proportionalpaymentcalculator.entities.Item;
import com.payment.proportionalpaymentcalculator.entities.Order;
import com.payment.proportionalpaymentcalculator.rest.PaymentRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class IncreaseFilter implements OrderFilter {

    @Override
    public void filter(PaymentRequest paymentRequest, Order order) {
        BigDecimal initialPriceItems = calculateInitialPrice(order);
        BigDecimal totalPriceItemsWithAdjustments = calculateTotalPrice(order);
        BigDecimal increasePerFriend = calculateIncreasePerFriend(paymentRequest, initialPriceItems);
        BigDecimal finalProportionalPrice = calculateFinalProportionalPrice(totalPriceItemsWithAdjustments, increasePerFriend);

        order.setOrderAmount(finalProportionalPrice);
    }

    private BigDecimal calculateInitialPrice(Order order) {
        return order.getItems().stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalPrice(Order order) {
        return order.getOrderAmount() == null ? calculateInitialPrice(order) : order.getOrderAmount();
    }

    private BigDecimal calculateIncreasePerFriend(PaymentRequest paymentRequest, BigDecimal initialPriceItems) {
        return initialPriceItems.multiply(paymentRequest.getIncrease())
                .divide(paymentRequest.getTotalAmount(), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateFinalProportionalPrice(BigDecimal totalPriceItemsWithAdjustments, BigDecimal increasePerFriend) {
        return totalPriceItemsWithAdjustments.add(increasePerFriend);
    }
}
