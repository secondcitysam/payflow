package com.payflow.bankservice.mapper;

import com.payflow.bankservice.dto.CreateAccountRequest;
import com.payflow.bankservice.entity.Account;

public class AccountMapper {

    private AccountMapper() {
    }

    public static Account toEntity(
            CreateAccountRequest request
    ) {

        return Account.builder()
                .accountNumber(
                        request.getAccountNumber()
                )
                .customerName(
                        request.getCustomerName()
                )
                .bankType(
                        request.getBankType()
                )
                .balance(
                        request.getOpeningBalance()
                )
                .build();
    }
}