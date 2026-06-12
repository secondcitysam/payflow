package com.payflow.common.event;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitRequestedEvent {

    private String transactionReference;
    private String receiverAccount;
    private String accountNumber;

    private BigDecimal amount;


}