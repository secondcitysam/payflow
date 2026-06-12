package com.payflow.switchservice.consumer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.enums.TransactionStatus;
import com.payflow.common.event.ReversalCompletedEvent;
import com.payflow.switchservice.entity.Transaction;
import com.payflow.switchservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReversalCompletedConsumer {

    private final TransactionRepository repository;

    @KafkaListener(
            topics = KafkaTopics.REVERSAL_COMPLETED,
            groupId = "switch-group"
    )
    public void consume(
            ReversalCompletedEvent event
    ) {

        Transaction transaction =
                repository
                        .findByTransactionReference(
                                event.getTransactionReference()
                        )
                        .orElseThrow();

        transaction.setStatus(
                TransactionStatus.REVERSED
        );

        repository.save(transaction);
    }
}