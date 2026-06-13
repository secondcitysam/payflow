package com.payflow.analyticsservice.controller;

import com.payflow.analyticsservice.entity.AnalyticsSummary;
import com.payflow.analyticsservice.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/analytics")
@RequiredArgsConstructor
public class AnalyticsController {

    private final AnalyticsService service;

    @GetMapping("/dashboard")
    public AnalyticsSummary dashboard() {

        return service.getSummary();
    }
}