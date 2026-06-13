package com.payflow.analyticsservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalyticsResponse {

    private Long totalTransactions;

    private Long successfulTransactions;

    private Long manualReviewTransactions;

    private Long failedTransactions;

    private Double totalAmount;
}