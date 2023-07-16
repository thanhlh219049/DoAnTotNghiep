package com.lethanh219049.application.kafka.config;


import com.lethanh219049.application.kafka.KafkaService;
import com.lethanh219049.application.entity.TaskBatch;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.*;

import static com.lethanh219049.application.config.Constant.CHANGE_PASSWORD;


//@Configuration
//@EnableKafka
public class KafkaConfig {

    @Value("${numConsumersAndPartition}")
    private Integer numConsumersAndPartition;
    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${kafka.bootstrap-servers-consumer}")
    private String bootstrapServersConsumer;

    @Bean
    public KafkaService kafkaService() {
        return new KafkaService();
    }

    @Bean
    public ArrayList<NewTopic> listProducerTopics() {
        ArrayList<NewTopic> listTopics = new ArrayList<>();
        listTopics.add(new NewTopic("my-topic",3, (short) 1));
        return listTopics;
    }

    @Bean
    public ArrayList<String> listConsumerTopics() {
        ArrayList<String> listTopics = new ArrayList<>();
        listTopics.add("my-topic");
        return listTopics;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return configProps;
    }

    @Bean
    public ProducerFactory<Object, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public KafkaTemplate<Object, Object> kafkaTemplate() {
        return new KafkaTemplate<Object, Object>(producerFactory());
    }


  //=============================================SEND_Email==========================================================

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, TaskBatch> taskBatchKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TaskBatch> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(taskBatchConsumerFactory());
        return factory;
    }


    public ConsumerFactory<String, TaskBatch> taskBatchConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        String topic = CHANGE_PASSWORD;
        //todo: config
        Integer numConsumers = numConsumersAndPartition;

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServersConsumer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "send-email-consumer");
//        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, String.valueOf(100));
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 300000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);

        //================================================================================= 3 consumer tro vao 3 partition
        // Create consumers
        List<KafkaConsumer<String, TaskBatch>> consumers = new ArrayList<>();
        for (int i = 0; i < numConsumers; i++) {
            KafkaConsumer<String, TaskBatch> consumer = new KafkaConsumer<>(props);
            consumers.add(consumer);
        }
        // Assign partitions to consumers
        List<TopicPartition> partitions = new ArrayList<>();
        for (int i = 0; i < numConsumers; i++) {
            partitions.add(new TopicPartition(topic, i));
        }


        for(int i=0; i < numConsumers; i++) {
            var consumer = consumers.get(i);
            consumer.assign( Arrays.asList(partitions.get(i)));
//
//            consumer.poll(Duration.ofMillis(100));
//            consumer.close();

        }

        return new DefaultKafkaConsumerFactory<>(
                props,
                new StringDeserializer(),
                new JsonDeserializer<>(TaskBatch.class));
    }
}
