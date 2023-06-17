package com.lethanh219049.application.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.KafkaAdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.*;

public class KafkaService {
    public KafkaService() {
    }

    public void sendJsonToTopic(Properties props, String topic, String key, Object value) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer(props);
        kafkaProducer.send(new ProducerRecord(topic, key, objectMapper.writeValueAsString(value)));
        kafkaProducer.close();
    }

    public void sendListJsonToTopic(Properties props, String topic, Map<String, Object> records) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer(props);
        Set<String> keys = records.keySet();
        Iterator var7 = keys.iterator();

        while (var7.hasNext()) {
            String key = (String) var7.next();
            kafkaProducer.send(new ProducerRecord(topic, key, objectMapper.writeValueAsString(records.get(key))));
        }

        kafkaProducer.close();
    }

    public void unSubscribeTopic(Properties props, String topic) {
        KafkaConsumer<String, String> consumer = new KafkaConsumer(props);
        consumer.unsubscribe();
        consumer.close();
    }

    public void deleteTopic(Properties props, List<String> topics) {
        KafkaAdminClient adminClient = (KafkaAdminClient) AdminClient.create(props);
        adminClient.deleteTopics(topics);
        adminClient.close();
    }

    public void createTopics(Properties kafkaAdminProperties, List<NewTopic> listTopics) {
        KafkaAdminClient kafkaAdminClient = (KafkaAdminClient) AdminClient.create(kafkaAdminProperties);
        kafkaAdminClient.createTopics(listTopics);
        kafkaAdminClient.close();
    }
}