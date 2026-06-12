package com.payflow.switchservice.consumer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.CreditRequestedEvent;
import com.payflow.common.event.DebitCompletedEvent;
import com.payflow.common.enums.TransactionStatus;
import com.payflow.switchservice.entity.Transaction;
import com.payflow.switchservice.producer.CreditRequestedProducer;
import com.payflow.switchservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DebitCompletedConsumer {

    private final TransactionRepository repository;

    private final CreditRequestedProducer producer;

    @KafkaListener(
            topics = KafkaTopics.DEBIT_COMPLETED,
            groupId = "switch-group"
    )
    public void consume(
            DebitCompletedEvent event
    ) {

        Transaction transaction =
                repository
                        .findByTransactionReference(
                                event.getTransactionReference()
                        )
                        .orElseThrow();

        if(!event.isSuccess())
        {
            transaction.setStatus(
                    TransactionStatus.FAILED
            );

            repository.save(transaction);

            return;
        }

        transaction.setStatus(
                TransactionStatus.DEBITED
        );

        repository.save(transaction);

        producer.publish(

                CreditRequestedEvent.builder()
                        .transactionReference(
                                transaction.getTransactionReference()
                        )
                        .accountNumber(
                                transaction.getReceiverAccount()
                        )
                        .amount(
                                transaction.getAmount()
                        )
                        .build()
        );
    }
}