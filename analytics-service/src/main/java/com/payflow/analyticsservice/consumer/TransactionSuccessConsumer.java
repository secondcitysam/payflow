package com.payflow.analyticsservice.consumer;

import com.payflow.analyticsservice.entity.AnalyticsSummary;
import com.payflow.analyticsservice.service.AnalyticsService;
import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.TransactionSuccessEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionSuccessConsumer {

    private final AnalyticsService analyticsService;

    @KafkaListener(
            topics = KafkaTopics.TRANSACTION_SUCCESS,
            groupId = "analytics-group"
    )
    public void consume(
            TransactionSuccessEvent event
    ) {

        AnalyticsSummary summary =
                analyticsService.getSummary();

        summary.setSuccessfulTransactions(
                summary.getSuccessfulTransactions()
                        + 1
        );

        analyticsService.save(summary);
    }
}