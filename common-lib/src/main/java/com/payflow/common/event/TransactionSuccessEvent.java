package com.payflow.common.event;

import com.payflow.common.enums.BankType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionSuccessEvent {

    private String transactionReference;

    private BankType senderBank;

    private BankType receiverBank;

    private BigDecimal amount;
}