package com.github.escalhao.kafka.avro.v1.producer;

import com.github.escalhao.Customer;
import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.record.CompressionType;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;

public class KafkaAvroProducer {

    final static String kafkaBrokers = "127.0.0.1:9092";
    final static String schemaRegistryUrl = "http://127.0.0.1:8081";

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(KafkaAvroProducer.class.getName());
        String topic = "customer-avro";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBrokers);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, CompressionType.GZIP.name);
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32*1024));
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));
        properties.setProperty(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

        KafkaProducer<String, Customer> kafkaProducer = new KafkaProducer<>(properties);

        Customer customer = Customer.newBuilder()
            .setFirstName("Naty")
            .setLastName("Bla3")
            .setAge(28)
            .setHeight(1.79f)
            .setWeight(63.0f)
            .setAutomatedEmail(false)
            .setEmails(Arrays.asList("escalhao3@gmail.com","escalhao4@gmail.com"))
            .build();

        ProducerRecord<String, Customer> customerRecord = new ProducerRecord<>(topic, customer);

        kafkaProducer.send(customerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    logger.error("Error sending Customer record " + customerRecord.value() + " to Kafka. Error: ", exception);
                } else {
                    logger.info("Customer record " + customerRecord.value() + " sent to Kafka topic " + metadata.topic() + " to partition " + metadata.partition());
                }
            }
        });

        kafkaProducer.flush();
        kafkaProducer.close();
    }

}
