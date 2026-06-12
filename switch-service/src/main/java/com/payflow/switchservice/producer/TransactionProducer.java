package com.payflow.switchservice.producer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.TransactionCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishTransactionCreatedEvent(
            TransactionCreatedEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.TRANSACTION_CREATED,
                event.getTransactionReference(),
                event
        );
    }
}