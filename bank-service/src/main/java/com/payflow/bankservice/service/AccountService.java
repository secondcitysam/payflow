package com.payflow.bankservice.service;

import com.payflow.bankservice.entity.Account;

import java.math.BigDecimal;

public interface AccountService {

    Account createAccount(
            Account account
    );

    Account debit(
            String accountNumber,
            BigDecimal amount
    );

    Account credit(
            String accountNumber,
            BigDecimal amount
    );

    Account getByAccountNumber(
            String accountNumber
    );
}