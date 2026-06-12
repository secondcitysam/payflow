package com.payflow.switchservice.dto.request;

import com.payflow.common.enums.BankType;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateTransactionRequest {
    @NotBlank
    private String idempotencyKey;

    @NotBlank
    private String senderAccount;

    @NotBlank
    private String receiverAccount;

    @NotNull
    private BankType senderBank;

    @NotNull
    private BankType receiverBank;

    @NotNull
    @DecimalMin(value = "1.00")
    private BigDecimal amount;
}