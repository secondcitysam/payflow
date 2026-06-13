package com.payflow.switchservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.payflow.switchservice.entity.OutboxEvent;
import com.payflow.switchservice.repository.OutboxEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OutboxService {

    private final OutboxEventRepository repository;

    private final ObjectMapper objectMapper;

    public void saveEvent(
            String topic,
            Object payload
    ) {

        try {

            repository.save(

                    OutboxEvent.builder()
                            .topicName(topic)
                            .payload(
                                    objectMapper
                                            .writeValueAsString(
                                                    payload
                                            )
                            )
                            .published(false)
                            .createdAt(
                                    LocalDateTime.now()
                            )
                            .build()
            );

        } catch (Exception ex) {

            throw new RuntimeException(
                    ex
            );
        }
    }
}