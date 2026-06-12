package com.payflow.auditservice.consumer;

import com.payflow.auditservice.entity.TransactionAudit;
import com.payflow.auditservice.repository.TransactionAuditRepository;
import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.TransactionCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class TransactionCreatedConsumer {

    private final TransactionAuditRepository repository;

    @KafkaListener(
            topics = KafkaTopics.TRANSACTION_CREATED,
            groupId = "audit-group"
    )
    public void consume(
            TransactionCreatedEvent event
    ) {

        TransactionAudit audit =
                TransactionAudit.builder()
                        .transactionReference(
                                event.getTransactionReference()
                        )
                        .senderAccount(
                                event.getSenderAccount()
                        )
                        .receiverAccount(
                                event.getReceiverAccount()
                        )
                        .senderBank(
                                event.getSenderBank()
                        )
                        .receiverBank(
                                event.getReceiverBank()
                        )
                        .amount(
                                event.getAmount()
                        )
                        .receivedAt(
                                LocalDateTime.now()
                        )
                        .build();

        repository.save(audit);
    }
}