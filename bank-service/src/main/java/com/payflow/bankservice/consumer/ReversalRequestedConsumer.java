package com.payflow.bankservice.consumer;

import com.payflow.bankservice.producer.ReversalCompletedProducer;
import com.payflow.bankservice.service.AccountService;
import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.ReversalCompletedEvent;
import com.payflow.common.event.ReversalRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReversalRequestedConsumer {

    private final AccountService accountService;

    private final ReversalCompletedProducer producer;

    @KafkaListener(
            topics = KafkaTopics.REVERSAL_REQUESTED,
            groupId = "bank-group"
    )
    public void consume(
            ReversalRequestedEvent event
    ) {

        try {

            accountService.credit(
                    event.getAccountNumber(),
                    event.getAmount()
            );

            producer.publish(
                    ReversalCompletedEvent.builder()
                            .transactionReference(
                                    event.getTransactionReference()
                            )
                            .success(true)
                            .message(
                                    "Reversal successful"
                            )
                            .build()
            );

        } catch (Exception ex) {

            producer.publish(
                    ReversalCompletedEvent.builder()
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