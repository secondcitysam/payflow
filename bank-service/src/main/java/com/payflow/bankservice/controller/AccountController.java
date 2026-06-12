package com.payflow.bankservice.controller;

import com.payflow.bankservice.dto.AccountResponse;
import com.payflow.bankservice.dto.CreateAccountRequest;
import com.payflow.bankservice.entity.Account;
import com.payflow.bankservice.mapper.AccountMapper;
import com.payflow.bankservice.service.AccountService;
import com.payflow.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public ApiResponse<AccountResponse>
    createAccount(
            @RequestBody
            CreateAccountRequest request
    ) {

        Account account =
                AccountMapper.toEntity(
                        request
                );

        Account saved =
                accountService.createAccount(
                        account
                );

        return new ApiResponse<>(
                true,
                "Account created successfully",
                AccountResponse.builder()
                        .accountNumber(
                                saved.getAccountNumber()
                        )
                        .customerName(
                                saved.getCustomerName()
                        )
                        .bankType(
                                saved.getBankType()
                        )
                        .balance(
                                saved.getBalance()
                        )
                        .build()
        );
    }

    @GetMapping("/{accountNumber}")
    public ApiResponse<AccountResponse>
    getAccount(
            @PathVariable
            String accountNumber
    ) {

        Account account =
                accountService
                        .getByAccountNumber(
                                accountNumber
                        );

        return new ApiResponse<>(
                true,
                "Account fetched successfully",
                AccountResponse.builder()
                        .accountNumber(
                                account.getAccountNumber()
                        )
                        .customerName(
                                account.getCustomerName()
                        )
                        .bankType(
                                account.getBankType()
                        )
                        .balance(
                                account.getBalance()
                        )
                        .build()
        );
    }
}