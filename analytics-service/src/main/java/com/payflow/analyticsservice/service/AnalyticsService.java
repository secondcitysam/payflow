package com.payflow.analyticsservice.service;

import com.payflow.analyticsservice.entity.AnalyticsSummary;
import com.payflow.analyticsservice.repository.AnalyticsSummaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnalyticsService {

    private final AnalyticsSummaryRepository repository;

    public AnalyticsSummary getSummary() {

        return repository
                .findById(1L)
                .orElseGet(() -> {

                    AnalyticsSummary summary =
                            AnalyticsSummary.builder()
                                    .id(1L)
                                    .totalTransactions(0L)
                                    .successfulTransactions(0L)
                                    .manualReviewTransactions(0L)
                                    .failedTransactions(0L)
                                    .totalAmount(0.0)
                                    .build();

                    return repository.save(summary);
                });
    }

    public void save(
            AnalyticsSummary summary
    ) {
        repository.save(summary);
    }
}