package com.payment.proportionalpaymentcalculator.filters;

import com.payment.proportionalpaymentcalculator.entities.Item;
import com.payment.proportionalpaymentcalculator.entities.Order;
import com.payment.proportionalpaymentcalculator.rest.PaymentRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DiscountPercentageFilter implements OrderFilter {

    @Override
    public void paymentFilter(PaymentRequest paymentRequest, Order order) {
        BigDecimal initialPriceItems = calculateInitialPrice(order);
        BigDecimal totalPriceItemsWithAdjustments = calculateTotalPrice(order);
        BigDecimal discountAmount = calculateDiscountAmount(paymentRequest, initialPriceItems);
        BigDecimal finalProportionalPrice = calculateFinalProportionalPrice(totalPriceItemsWithAdjustments, discountAmount);

        order.setOrderAmount(finalProportionalPrice);
    }

    private BigDecimal calculateInitialPrice(Order order) {
        return order.getItems().stream()
                .map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calculateTotalPrice(Order order) {
        return order.getOrderAmount() == null ? calculateInitialPrice(order)
                : order.getOrderAmount();
    }

    private BigDecimal calculateDiscountAmount(PaymentRequest paymentRequest, BigDecimal initialPriceItems) {
        BigDecimal discountPercent = BigDecimal.valueOf(paymentRequest.getDiscountPercent());
        return initialPriceItems.multiply(discountPercent.divide(BigDecimal.valueOf(100)))
                .setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateFinalProportionalPrice(BigDecimal totalPriceItemsWithAdjustments, BigDecimal discountAmount) {
        return totalPriceItemsWithAdjustments.subtract(discountAmount);
    }
}
