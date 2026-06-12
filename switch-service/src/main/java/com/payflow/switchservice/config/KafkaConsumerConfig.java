package com.payflow.switchservice.config;

import com.payflow.common.event.TransactionFraudResultEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Bean
    public JsonDeserializer<TransactionFraudResultEvent>
    fraudDeserializer() {

        return new JsonDeserializer<>(
                TransactionFraudResultEvent.class
        );
    }
}