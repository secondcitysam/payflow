package com.payflow.analyticsservice.consumer;

import com.payflow.analyticsservice.entity.AnalyticsSummary;
import com.payflow.analyticsservice.service.AnalyticsService;
import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.TransactionCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionCreatedConsumer {

    private final AnalyticsService analyticsService;

    @KafkaListener(
            topics = KafkaTopics.TRANSACTION_CREATED,
            groupId = "analytics-group"
    )
    public void consume(
            TransactionCreatedEvent event
    ) {

        AnalyticsSummary summary =
                analyticsService.getSummary();

        summary.setTotalTransactions(
                summary.getTotalTransactions() + 1
        );

        summary.setTotalAmount(
                summary.getTotalAmount()
                        + event.getAmount().doubleValue()
        );

        analyticsService.save(summary);
    }
}