package com.payflow.switchservice.scheduler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.payflow.switchservice.entity.OutboxEvent;
import com.payflow.switchservice.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxPublisherScheduler {

    private final OutboxEventRepository
            repository;

    private final KafkaTemplate<String,Object>
            kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Scheduled(
            fixedDelay = 5000
    )
    public void publish() {

        List<OutboxEvent> events =
                repository
                        .findByPublishedFalse();

        for(OutboxEvent event : events)
        {
            try {

                JsonNode payload =
                        objectMapper
                                .readTree(
                                        event.getPayload()
                                );

                kafkaTemplate.send(
                        event.getTopicName(),
                        payload
                );

                event.setPublished(
                        true
                );

                repository.save(
                        event
                );

            }
            catch(Exception ignored)
            {

            }
        }
    }
}