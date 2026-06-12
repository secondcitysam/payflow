package com.payflow.bankservice.consumer;

import com.payflow.bankservice.service.AccountService;
import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.DebitCompletedEvent;
import com.payflow.common.event.DebitRequestedEvent;
import com.payflow.bankservice.producer.DebitCompletedProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DebitRequestedConsumer {

    private final AccountService accountService;

    private final DebitCompletedProducer producer;
    private String receiverAccount;

    @KafkaListener(
            topics = KafkaTopics.DEBIT_REQUESTED,
            groupId = "bank-group"
    )
    public void consume(
            DebitRequestedEvent event
    ) {

        try {

            accountService.debit(
                    event.getAccountNumber(),
                    event.getAmount()
            );

            producer.publish(
                    DebitCompletedEvent.builder()
                            .transactionReference(
                                    event.getTransactionReference()
                            )
                            .receiverAccount(
                                    event.getReceiverAccount()
                            )
                            .success(true)
                            .message(
                                    "Debit successful"
                            )
                            .build()
            );

        } catch (Exception ex) {

            producer.publish(
                    DebitCompletedEvent.builder()
                            .transactionReference(
                                    event.getTransactionReference()
                            )
                            .success(false)
                            .message(
                                    ex.getMessage()
                            )
                            .build()
            );
        }
    }
}