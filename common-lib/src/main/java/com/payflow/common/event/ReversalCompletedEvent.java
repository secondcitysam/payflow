package com.payflow.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReversalCompletedEvent {

    private String transactionReference;

    private boolean success;

    private String message;
}