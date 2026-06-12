package com.payflow.fraudservice.config;

import com.payflow.common.constants.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic fraudResultTopic() {

        return new NewTopic(
                KafkaTopics.TRANSACTION_FRAUD_RESULT,
                3,
                (short) 1
        );
    }
}