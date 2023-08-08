package com.payment.proportionalpaymentcalculator.rest;

import com.payment.proportionalpaymentcalculator.dto.PaymentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponse {

    private List<PaymentDTO> paymentsLink;
}
