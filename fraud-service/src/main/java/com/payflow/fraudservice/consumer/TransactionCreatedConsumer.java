package com.payflow.fraudservice.consumer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.enums.FraudResult;
import com.payflow.common.event.TransactionCreatedEvent;
import com.payflow.common.event.TransactionFraudResultEvent;
import com.payflow.fraudservice.producer.FraudResultProducer;
import com.payflow.fraudservice.service.FraudRuleService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionCreatedConsumer {

    private final FraudRuleService fraudRuleService;
    private final FraudResultProducer producer;

    @KafkaListener(
            topics = KafkaTopics.TRANSACTION_CREATED,
            groupId = "fraud-group"
    )
    public void consume(
            TransactionCreatedEvent event
    ) {

        FraudResult result =
                fraudRuleService.evaluate(event);

        TransactionFraudResultEvent fraudResultEvent =
                TransactionFraudResultEvent.builder()
                        .transactionReference(
                                event.getTransactionReference()
                        )
                        .fraudResult(result)
                        .reason(
                                result == FraudResult.APPROVED
                                        ? "Transaction approved"
                                        : "Large amount detected"
                        )
                        .build();

        producer.publish(fraudResultEvent);
    }
}