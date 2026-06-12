package com.payflow.common.event;

import com.payflow.common.enums.BankType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionCreatedEvent {

    private String transactionReference;

    private String senderAccount;

    private String receiverAccount;

    private BankType senderBank;

    private BankType receiverBank;

    private BigDecimal amount;
}