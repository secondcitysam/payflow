package com.payflow.bankservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaRetryTopic;
import org.springframework.kafka.retrytopic.RetryTopicConfigurationSupport;

@Configuration
@EnableKafkaRetryTopic
public class RetryConfig
        extends RetryTopicConfigurationSupport {
}