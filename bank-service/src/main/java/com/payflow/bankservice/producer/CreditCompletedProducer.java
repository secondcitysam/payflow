package com.payflow.bankservice.producer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.CreditCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreditCompletedProducer {

    private final KafkaTemplate<String,Object>
            kafkaTemplate;

    public void publish(
            CreditCompletedEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.CREDIT_COMPLETED,
                event.getTransactionReference(),
                event
        );
    }
}