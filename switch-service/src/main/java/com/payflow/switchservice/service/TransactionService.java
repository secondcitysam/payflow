package com.payflow.switchservice.service;

import com.payflow.switchservice.entity.Transaction;

public interface TransactionService {

    Transaction createTransaction(
            Transaction transaction,
            String idempotencyKey
    );

    Transaction getByReference(
            String reference
    );
}