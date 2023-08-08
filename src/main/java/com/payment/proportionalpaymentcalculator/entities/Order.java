package com.payment.proportionalpaymentcalculator.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private List<Item> items;
    private String nameOfFriend;
    private BigDecimal orderAmount;
}
