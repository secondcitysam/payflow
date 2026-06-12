package com.payflow.common.event;

import com.payflow.common.enums.FraudResult;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionFraudResultEvent {

    private String transactionReference;

    private FraudResult fraudResult;

    private String reason;
}