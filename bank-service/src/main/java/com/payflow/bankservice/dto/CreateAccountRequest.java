package com.payflow.bankservice.dto;

import com.payflow.common.enums.BankType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateAccountRequest {

    private String accountNumber;

    private String customerName;

    private BankType bankType;

    private BigDecimal openingBalance;
}