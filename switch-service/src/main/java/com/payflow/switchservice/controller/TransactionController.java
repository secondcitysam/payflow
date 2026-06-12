package com.payflow.switchservice.controller;

import com.payflow.common.dto.ApiResponse;
import com.payflow.switchservice.dto.request.CreateTransactionRequest;
import com.payflow.switchservice.dto.response.TransactionResponse;
import com.payflow.switchservice.entity.Transaction;
import com.payflow.switchservice.mapper.TransactionMapper;
import com.payflow.switchservice.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(
            summary = "Create transaction"
    )
    @PostMapping
    public ApiResponse<TransactionResponse> createTransaction(
            @Valid @RequestBody CreateTransactionRequest request
    ) {

        Transaction transaction =
                TransactionMapper.toEntity(request);

        Transaction savedTransaction =
                transactionService.createTransaction(
                        transaction,
                        request.getIdempotencyKey()
                );

        return new ApiResponse<>(
                true,
                "Transaction created successfully",
                TransactionMapper.toResponse(savedTransaction)
        );
    }

    @Operation(
            summary = "Get transaction by reference"
    )
    @GetMapping("/{reference}")
    public ApiResponse<TransactionResponse> getTransaction(
            @PathVariable String reference
    ) {

        Transaction transaction =
                transactionService.getByReference(reference);

        return new ApiResponse<>(
                true,
                "Transaction fetched successfully",
                TransactionMapper.toResponse(transaction)
        );
    }
}