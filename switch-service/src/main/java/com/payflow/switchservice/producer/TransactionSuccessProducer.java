package com.payflow.switchservice.producer;

import com.payflow.common.event.TransactionSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionSuccessProducer {

    private final KafkaTemplate<String,Object>
            kafkaTemplate;

    public void publish(
            TransactionSuccessEvent event
    ) {

        kafkaTemplate.send(
                "transaction-success",
                event.getTransactionReference(),
                event
        );
    }
}