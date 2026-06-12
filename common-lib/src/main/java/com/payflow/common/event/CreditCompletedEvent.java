package com.payflow.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreditCompletedEvent {

    private String transactionReference;

    private boolean success;

    private String message;
}