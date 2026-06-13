package com.payflow.settlementservice.consumer;

import com.payflow.common.event.TransactionSuccessEvent;
import com.payflow.settlementservice.entity.Settlement;
import com.payflow.settlementservice.repository.SettlementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionSuccessConsumer {

    private final SettlementRepository repository;

    @KafkaListener(
            topics = "transaction-success",
            groupId = "settlement-group"
    )
    public void consume(
            TransactionSuccessEvent event
    ) {

        repository.save(

                Settlement.builder()
                        .senderBank(
                                event.getSenderBank().name()
                        )
                        .receiverBank(
                                event.getReceiverBank().name()
                        )
                        .amount(
                                event.getAmount()
                        )
                        .build()
        );
    }
}