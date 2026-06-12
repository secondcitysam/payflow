package com.payflow.fraudservice.producer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.TransactionFraudResultEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FraudResultProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publish(
            TransactionFraudResultEvent event
    ) {

        kafkaTemplate.send(
                KafkaTopics.TRANSACTION_FRAUD_RESULT,
                event.getTransactionReference(),
                event
        );
    }
}