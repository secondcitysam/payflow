package com.payflow.switchservice.producer;

import com.payflow.common.constants.KafkaTopics;
import com.payflow.common.event.DebitRequestedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DebitRequestedProducer {

    private final KafkaTemplate<String,Object>
            kafkaTemplate;

    public void publish(
            DebitRequestedEvent event
    ) {

        System.out.println(
                "STEP 5"
        );

        kafkaTemplate.send(
                KafkaTopics.DEBIT_REQUESTED,
                event.getTransactionReference(),
                event
        ).whenComplete((result, ex) -> {

            if(ex != null)
            {
                System.out.println(
                        "STEP 6 FAILED"
                );

                ex.printStackTrace();
            }
            else
            {
                System.out.println(
                        "STEP 6 SUCCESS"
                );
            }
        });
    }
}