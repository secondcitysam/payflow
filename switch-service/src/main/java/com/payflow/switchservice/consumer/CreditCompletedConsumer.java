package com.payflow.switchservice.consumer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.enums.TransactionStatus;
import com.payflow.common.event.CreditCompletedEvent;
import com.payflow.switchservice.entity.Transaction;
import com.payflow.switchservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditCompletedConsumer {

    private final TransactionRepository repository;

    @KafkaListener(
            topics = KafkaTopics.CREDIT_COMPLETED,
            groupId = "switch-group"
    )
    public void consume(
            CreditCompletedEvent event
    ) {

        Transaction transaction =
                repository
                        .findByTransactionReference(
                                event.getTransactionReference()
                        )
                        .orElseThrow();

        if(event.isSuccess())
        {
            transaction.setStatus(
                    TransactionStatus.SUCCESS
            );
        }
        else
        {
            transaction.setStatus(
                    TransactionStatus.FAILED
            );
        }

        repository.save(transaction);
    }
}