package com.payflow.bankservice.producer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.DebitCompletedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DebitCompletedProducer {

    private final KafkaTemplate<String,Object>
            kafkaTemplate;

    public void publish(
            DebitCompletedEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.DEBIT_COMPLETED,
                event.getTransactionReference(),
                event
        );
    }
}