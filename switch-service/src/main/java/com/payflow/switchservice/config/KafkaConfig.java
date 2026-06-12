package com.payflow.switchservice.config;

import com.payflow.common.constants.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic transactionCreatedTopic() {

        return new NewTopic(
                KafkaTopics.TRANSACTION_CREATED,
                3,
                (short) 1
        );
    }
}