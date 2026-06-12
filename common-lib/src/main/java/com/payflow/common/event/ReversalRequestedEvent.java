package com.payflow.common.event;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReversalRequestedEvent {

    private String transactionReference;

    private String accountNumber;

    private BigDecimal amount;
}