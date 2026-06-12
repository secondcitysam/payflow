package com.payflow.bankservice.dto;

import com.payflow.common.enums.BankType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class AccountResponse {

    private String accountNumber;

    private String customerName;

    private BankType bankType;

    private BigDecimal balance;
}