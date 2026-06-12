package com.payflow.auditservice.config;

import com.payflow.common.event.TransactionCreatedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public JsonDeserializer<TransactionCreatedEvent>
    transactionCreatedEventDeserializer() {

        return new JsonDeserializer<>(
                TransactionCreatedEvent.class
        );
    }
}