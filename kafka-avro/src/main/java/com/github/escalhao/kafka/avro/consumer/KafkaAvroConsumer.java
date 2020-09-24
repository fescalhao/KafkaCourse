package com.github.escalhao.kafka.avro.consumer;

import com.github.escalhao.Customer;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class KafkaAvroConsumer {

    final static String kafkaBrokers = "127.0.0.1:9092";
    final static String schemaRegistryUrl = "http://127.0.0.1:8081";
    final static String kafkaAvroConsumerGroupId = "avro-consumer";

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(KafkaAvroConsumer.class.getName());
        String topic = "customer-avro";

        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, kafkaAvroConsumerGroupId);
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "200");
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, OffsetResetStrategy.EARLIEST.name().toLowerCase());
        properties.setProperty(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        properties.setProperty(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, "true");

        KafkaConsumer<String, Customer> kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Collections.singleton(topic));

        logger.info("Waiting for data...");
        ConsumerRecords<String, Customer> consumerRecords;

        try {
            while (true) {
                consumerRecords = kafkaConsumer.poll(Duration.ofMillis(500));
                consumerRecords.forEach(consumerRecord -> {
                    Customer customer = consumerRecord.value();
                    logger.info("Customer " + customer.getFirstName() + " retrieved");
                    logger.info("Full Customer data: " + customer.toString());
                });
                kafkaConsumer.commitSync();
            }
        } catch (Exception exception) {
            logger.error("Error polling data from Kafka. Error: ", exception);
        } finally {
            kafkaConsumer.close();
        }
    }

}
