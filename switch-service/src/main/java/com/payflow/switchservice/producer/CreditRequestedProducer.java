package com.payflow.switchservice.producer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.CreditRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditRequestedProducer {

    private final KafkaTemplate<String,Object>
            kafkaTemplate;

    public void publish(
            CreditRequestedEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.CREDIT_REQUESTED,
                event.getTransactionReference(),
                event
        );
    }
}