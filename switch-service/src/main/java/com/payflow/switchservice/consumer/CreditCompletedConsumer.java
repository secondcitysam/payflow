package com.payflow.switchservice.consumer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.enums.TransactionStatus;
import com.payflow.common.event.CreditCompletedEvent;
import com.payflow.common.event.ReversalRequestedEvent;
import com.payflow.common.event.TransactionSuccessEvent;
import com.payflow.switchservice.entity.Transaction;
import com.payflow.switchservice.producer.ReversalRequestedProducer;
import com.payflow.switchservice.producer.TransactionSuccessProducer;
import com.payflow.switchservice.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditCompletedConsumer {

    private final TransactionRepository repository;

    private final ReversalRequestedProducer
            reversalProducer;

    private final TransactionSuccessProducer
            successProducer;

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

            successProducer.publish(

                    TransactionSuccessEvent.builder()
                            .transactionReference(
                                    transaction.getTransactionReference()
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
                            .build()
            );
        }
        else
        {
            transaction.setStatus(
                    TransactionStatus.REVERSAL_INITIATED
            );

            reversalProducer.publish(

                    ReversalRequestedEvent
                            .builder()
                            .transactionReference(
                                    transaction.getTransactionReference()
                            )
                            .accountNumber(
                                    transaction.getSenderAccount()
                            )
                            .amount(
                                    transaction.getAmount()
                            )
                            .build()
            );
        }

        repository.save(transaction);
    }
}