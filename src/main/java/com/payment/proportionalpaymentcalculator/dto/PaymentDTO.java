package com.payment.proportionalpaymentcalculator.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PaymentDTO {

    private String nameOfFriend;
    private String paymentLink;
    private BigDecimal valueToPay;
}
