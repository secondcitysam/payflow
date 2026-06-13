package com.payflow.analyticsservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "analytics_summary")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnalyticsSummary {

    @Id
    private Long id;

    private Long totalTransactions;

    private Long successfulTransactions;

    private Long manualReviewTransactions;

    private Long failedTransactions;

    private Double totalAmount;
}