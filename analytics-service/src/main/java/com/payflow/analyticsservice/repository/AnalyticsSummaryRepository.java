package com.payflow.analyticsservice.repository;

import com.payflow.analyticsservice.entity.AnalyticsSummary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsSummaryRepository
        extends JpaRepository<
        AnalyticsSummary,
        Long
        > {
}