package com.payment.proportionalpaymentcalculator.filters;

import com.payment.proportionalpaymentcalculator.entities.Item;
import com.payment.proportionalpaymentcalculator.entities.Order;
import com.payment.proportionalpaymentcalculator.rest.PaymentRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class DiscountFilter implements OrderFilter {

    @Override
    public void paymentFilter(PaymentRequest paymentRequest, Order order) {
        BigDecimal initialPriceItems = calculateInitialPrice(order);
        BigDecimal totalPriceItemsWithDiscountsAndIncreases = calculateTotalPrice(order);
        BigDecimal discountPerFriend = calculateDiscountPerFriend(paymentRequest, initialPriceItems);
        BigDecimal finalProportionalPrice = calculateFinalProportionalPrice(totalPriceItemsWithDiscountsAndIncreases, discountPerFriend);

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

    private BigDecimal calculateDiscountPerFriend(PaymentRequest paymentRequest, BigDecimal initialPriceItems) {
        return initialPriceItems.multiply(paymentRequest.getDiscount())
                .divide(paymentRequest.getTotalAmount(), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateFinalProportionalPrice(BigDecimal totalPriceItemsWithDiscountsAndIncreases, BigDecimal discountPerFriend) {
        return totalPriceItemsWithDiscountsAndIncreases.subtract(discountPerFriend);
    }
}
