package com.payflow.switchservice.producer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.ReversalRequestedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReversalRequestedProducer {

    private final KafkaTemplate<String,Object>
            kafkaTemplate;

    public void publish(
            ReversalRequestedEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.REVERSAL_REQUESTED,
                event.getTransactionReference(),
                event
        );
    }
}