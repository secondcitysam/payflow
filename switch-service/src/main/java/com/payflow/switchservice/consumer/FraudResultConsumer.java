package com.payflow.switchservice.consumer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.enums.FraudResult;
import com.payflow.common.enums.TransactionStatus;
import com.payflow.common.event.DebitRequestedEvent;
import com.payflow.common.event.TransactionFraudResultEvent;
import com.payflow.switchservice.entity.Transaction;
import com.payflow.switchservice.producer.DebitRequestedProducer;
import com.payflow.switchservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FraudResultConsumer {

    private final TransactionRepository repository;

    private final DebitRequestedProducer
            debitRequestedProducer;

    @KafkaListener(
            topics = KafkaTopics.TRANSACTION_FRAUD_RESULT,
            groupId = "switch-group"
    )
    public void consume(
            TransactionFraudResultEvent event
    ) {

        log.info(
                "Fraud Result Received : {}",
                event
        );

        Transaction transaction =
                repository
                        .findByTransactionReference(
                                event.getTransactionReference()
                        )
                        .orElseThrow();

        if(event.getFraudResult()
                == FraudResult.APPROVED)
        {

            transaction.setStatus(
                    TransactionStatus.APPROVED
            );

            repository.save(transaction);

            DebitRequestedEvent debitRequestedEvent =
                    DebitRequestedEvent.builder()
                            .transactionReference(
                                    transaction.getTransactionReference()
                            )
                            .accountNumber(
                                    transaction.getSenderAccount()
                            )
                            .receiverAccount(
                                    transaction.getReceiverAccount()
                            )
                            .amount(
                                    transaction.getAmount()
                            )
                            .build();

            debitRequestedProducer.publish(
                    debitRequestedEvent
            );

        }
        else
        {
            transaction.setStatus(
                    TransactionStatus.MANUAL_REVIEW
            );

            repository.save(transaction);
        }

        log.info(
                "Transaction Updated : {}",
                transaction.getTransactionReference()
        );
    }
}