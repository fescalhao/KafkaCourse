package com.github.escalhao.kafka.tutorial1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerDemo {
    private static final String KAFKA_ADDRESS = "127.0.0.1:9092";
    private static final String GROUP_ID = "consumer-java";

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class);
        String topic = "first_topic";

        // Create Consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_ADDRESS);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Create Consumer
        KafkaConsumer<String,String> consumer = new KafkaConsumer<>(properties);

        // Subscribe Consumer to Topics
        // If you have more than one topic use consumer.subscribe(Arrays.asList("topic_1", "topic_2", "topic_n"...))
        consumer.subscribe(Collections.singleton(topic));

        try {
            while(true) {
                // Poll for new data from topic
                ConsumerRecords<String,String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String,String> record : records) {
                    logger.info("Key: " + record.key());
                    logger.info("Value: " + record.value() + "\n");
                }
            }
        } finally {
            consumer.close();
            logger.info("Consumer closed");
        }
    }
}
