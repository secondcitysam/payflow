package com.payflow.common.event;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DebitCompletedEvent {

    private String transactionReference;

    private String receiverAccount;

    private boolean success;

    private String message;
}