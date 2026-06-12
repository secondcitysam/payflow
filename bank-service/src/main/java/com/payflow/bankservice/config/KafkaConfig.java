package com.payflow.bankservice.config;

import com.payflow.common.constants.KafkaTopics;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic debitRequestedTopic() {

        return new NewTopic(
                KafkaTopics.DEBIT_REQUESTED,
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic debitCompletedTopic() {

        return new NewTopic(
                KafkaTopics.DEBIT_COMPLETED,
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic creditRequestedTopic() {

        return new NewTopic(
                KafkaTopics.CREDIT_REQUESTED,
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic creditCompletedTopic() {

        return new NewTopic(
                KafkaTopics.CREDIT_COMPLETED,
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic reversalRequestedTopic() {

        return new NewTopic(
                KafkaTopics.REVERSAL_REQUESTED,
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic reversalCompletedTopic() {

        return new NewTopic(
                KafkaTopics.REVERSAL_COMPLETED,
                3,
                (short) 1
        );
    }

    @Bean
    public NewTopic debitRequestedDltTopic() {

        return new NewTopic(
                KafkaTopics.DEBIT_REQUESTED_DLT,
                3,
                (short) 1
        );
    }
}