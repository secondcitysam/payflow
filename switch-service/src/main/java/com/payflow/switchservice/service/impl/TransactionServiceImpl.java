package com.payflow.switchservice.service.impl;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.enums.TransactionStatus;
import com.payflow.common.event.TransactionCreatedEvent;
import com.payflow.switchservice.entity.Transaction;
import com.payflow.switchservice.exception.BusinessException;
import com.payflow.switchservice.exception.DuplicateTransactionException;
import com.payflow.switchservice.exception.ResourceNotFoundException;
import com.payflow.switchservice.producer.TransactionProducer;
import com.payflow.switchservice.redis.RateLimitService;
import com.payflow.switchservice.redis.RedisService;
import com.payflow.switchservice.repository.TransactionRepository;
import com.payflow.switchservice.service.OutboxService;
import com.payflow.switchservice.service.TransactionService;
import com.payflow.switchservice.util.TransactionReferenceGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl
        implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final TransactionProducer transactionProducer;
    private final RedisService redisService;
    private final RateLimitService rateLimitService;
    private final OutboxService
            outboxService;
    @Override
    public Transaction createTransaction(
            Transaction transaction,
            String idempotencyKey
    ) {


        if(!redisService.acquireIdempotencyLock(
                idempotencyKey
        ))
        {
            throw new BusinessException(
                    "Duplicate Request"
            );
        }

        transaction.setTransactionReference(
                TransactionReferenceGenerator.generate()
        );

        transaction.setStatus(
                TransactionStatus.CREATED
        );

        Transaction savedTransaction =
                transactionRepository.save(
                        transaction
                );

        rateLimitService.validate(
                transaction.getSenderAccount()
        );

        redisService.saveIdempotencyKey(
                idempotencyKey,
                savedTransaction.getTransactionReference()
        );

        TransactionCreatedEvent event =
                TransactionCreatedEvent.builder()
                        .transactionReference(
                                savedTransaction.getTransactionReference()
                        )
                        .senderAccount(
                                savedTransaction.getSenderAccount()
                        )
                        .receiverAccount(
                                savedTransaction.getReceiverAccount()
                        )
                        .senderBank(
                                savedTransaction.getSenderBank()
                        )
                        .receiverBank(
                                savedTransaction.getReceiverBank()
                        )
                        .amount(
                                savedTransaction.getAmount()
                        )
                        .build();

        outboxService.saveEvent(
                KafkaTopics.TRANSACTION_CREATED,
                event
        );
        return savedTransaction;
    }

    @Override
    public Transaction getByReference(
            String reference
    ) {

        return transactionRepository
                .findByTransactionReference(reference)
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "Transaction not found"
                        )
                );
    }
}