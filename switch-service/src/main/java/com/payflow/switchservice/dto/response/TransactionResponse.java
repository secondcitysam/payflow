package com.payflow.switchservice.dto.response;

import com.payflow.common.enums.BankType;
import com.payflow.common.enums.TransactionStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {

    private String transactionReference;

    private String senderAccount;

    private String receiverAccount;

    private BankType senderBank;

    private BankType receiverBank;

    private BigDecimal amount;

    private TransactionStatus status;

    private LocalDateTime createdAt;
}