package com.payflow.fraudservice.service;

import com.payflow.common.enums.FraudResult;
import com.payflow.common.event.TransactionCreatedEvent;
import org.springframework.stereotype.Service;

@Service
public class FraudRuleService {

    public FraudResult evaluate(
            TransactionCreatedEvent event
    ) {

        if(event.getAmount().doubleValue() > 1000000)
        {
            return FraudResult.MANUAL_REVIEW;
        }

        return FraudResult.APPROVED;
    }
}