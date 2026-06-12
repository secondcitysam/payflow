package com.payflow.bankservice.service.impl;

import com.payflow.bankservice.entity.Account;
import com.payflow.bankservice.exception.InsufficientBalanceException;
import com.payflow.bankservice.repository.AccountRepository;
import com.payflow.bankservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl
        implements AccountService {

    private final AccountRepository repository;

    @Override
    public Account createAccount(
            Account account
    ) {
        return repository.save(account);
    }

    @Override
    public Account debit(
            String accountNumber,
            BigDecimal amount
    ) {

        Account account =
                repository
                        .findByAccountNumber(
                                accountNumber
                        )
                        .orElseThrow();

        if(account.getBalance()
                .compareTo(amount) < 0)
        {
            throw new InsufficientBalanceException(
                    "Insufficient balance"
            );
        }

        account.setBalance(
                account.getBalance()
                        .subtract(amount)
        );

        return repository.save(account);
    }

    @Override
    public Account credit(
            String accountNumber,
            BigDecimal amount
    ) {

        Account account =
                repository
                        .findByAccountNumber(
                                accountNumber
                        )
                        .orElseThrow();

        account.setBalance(
                account.getBalance()
                        .add(amount)
        );

        return repository.save(account);
    }

    @Override
    public Account getByAccountNumber(
            String accountNumber
    ) {

        return repository
                .findByAccountNumber(
                        accountNumber
                )
                .orElseThrow();
    }
}