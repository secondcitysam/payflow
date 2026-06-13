package com.payflow.analyticsservice.consumer;

import com.payflow.analyticsservice.entity.AnalyticsSummary;
import com.payflow.analyticsservice.service.AnalyticsService;
import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.enums.FraudResult;
import com.payflow.common.event.TransactionFraudResultEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FraudResultConsumer {

    private final AnalyticsService analyticsService;

    @KafkaListener(
            topics = KafkaTopics.TRANSACTION_FRAUD_RESULT,
            groupId = "analytics-group"
    )
    public void consume(
            TransactionFraudResultEvent event
    ) {

        if(event.getFraudResult()
                != FraudResult.MANUAL_REVIEW)
        {
            return;
        }

        AnalyticsSummary summary =
                analyticsService.getSummary();

        summary.setManualReviewTransactions(
                summary.getManualReviewTransactions()
                        + 1
        );

        analyticsService.save(summary);
    }
}