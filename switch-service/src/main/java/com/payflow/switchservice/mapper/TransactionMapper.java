package com.payflow.switchservice.mapper;

import com.payflow.switchservice.dto.request.CreateTransactionRequest;
import com.payflow.switchservice.dto.response.TransactionResponse;
import com.payflow.switchservice.entity.Transaction;

public class TransactionMapper {

    private TransactionMapper() {
    }

    public static Transaction toEntity(
            CreateTransactionRequest request
    ) {

        return Transaction.builder()
                .senderAccount(request.getSenderAccount())
                .receiverAccount(request.getReceiverAccount())
                .senderBank(request.getSenderBank())
                .receiverBank(request.getReceiverBank())
                .amount(request.getAmount())
                .build();
    }

    public static TransactionResponse toResponse(
            Transaction transaction
    ) {

        return TransactionResponse.builder()
                .transactionReference(
                        transaction.getTransactionReference()
                )
                .senderAccount(
                        transaction.getSenderAccount()
                )
                .receiverAccount(
                        transaction.getReceiverAccount()
                )
                .senderBank(
                        transaction.getSenderBank()
                )
                .receiverBank(
                        transaction.getReceiverBank()
                )
                .amount(
                        transaction.getAmount()
                )
                .status(
                        transaction.getStatus()
                )
                .createdAt(
                        transaction.getCreatedAt()
                )
                .build();
    }
}