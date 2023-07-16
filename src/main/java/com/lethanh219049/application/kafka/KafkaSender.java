package com.lethanh219049.application.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;


//@Component
public class KafkaSender {

    private KafkaTemplate<Object, Object> kafkaTemplate;
    private Logger log = LoggerFactory.getLogger(KafkaSender.class.getName());

    @Autowired
    KafkaSender(KafkaTemplate<Object, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendObject(String topicName, Object key, Object message) {
        log.info("vao check du lieu" + topicName + " - " + key + " - " + message);
        kafkaTemplate.send(topicName, key, message);
    }

    public void sendObject(String topic, Object message) {
        log.info("vao check du lieu" + " " + message);
        kafkaTemplate.send(topic, message);
    }

    void sendMessageWithCallback(String topicName, Object message, Object key,
                                 ListenableFutureCallback<SendResult<Object, Object>> callback) {
        ListenableFuture<SendResult<Object, Object>> future =
                kafkaTemplate.send(topicName, key, message);
        future.addCallback(callback);
    }
}
