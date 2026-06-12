package com.payflow.bankservice.consumer;

import com.payflow.bankservice.producer.CreditCompletedProducer;
import com.payflow.bankservice.service.AccountService;
import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.CreditCompletedEvent;
import com.payflow.common.event.CreditRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditRequestedConsumer {

    private final AccountService accountService;

    private final CreditCompletedProducer producer;

    @KafkaListener(
            topics = KafkaTopics.CREDIT_REQUESTED,
            groupId = "bank-group"
    )
    public void consume(
            CreditRequestedEvent event
    ) {

        try {

            accountService.credit(
                    event.getAccountNumber(),
                    event.getAmount()
            );

            producer.publish(
                    CreditCompletedEvent.builder()
                            .transactionReference(
                                    event.getTransactionReference()
                            )
                            .success(true)
                            .message(
                                    "Credit successful"
                            )
                            .build()
            );

        } catch (Exception ex) {

            producer.publish(
                    CreditCompletedEvent.builder()
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