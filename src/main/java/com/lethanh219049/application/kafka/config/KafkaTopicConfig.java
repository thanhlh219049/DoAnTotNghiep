package com.lethanh219049.application.kafka.config;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;

import static com.lethanh219049.application.config.Constant.CHANGE_PASSWORD;

//@Configuration
@ConditionalOnProperty(value = "kafka.enable", havingValue = "true", matchIfMissing = true)
class KafkaTopicConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${numConsumersAndPartition}")
    private Integer numConsumersAndPartition;

    @Value("${numBroker}")
    private int numBroker;// (short) = numBroker


    @Bean
    KafkaAdmin admin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new KafkaAdmin(configs);
    }

    // topic name được call thông qua kafka sender
    @Bean
    public NewTopic createEmail() {
        return new NewTopic(CHANGE_PASSWORD, numConsumersAndPartition, (short)1);
    }

}
