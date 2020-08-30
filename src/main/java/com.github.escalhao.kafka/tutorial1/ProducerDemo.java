package com.github.escalhao.kafka.tutorial1;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {
    private static final String KAFKA_ADDRESS = "127.0.0.1:9092";

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(ProducerDemo.class);

        // Create Producer properties
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_ADDRESS);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // Create Producer
        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        for (int i=0;i<10;i++){
            String topic = "first_topic";
            String value = "Producer - " + i;
            String key = "id_" + i;

            // Create Producer Record
            ProducerRecord<String,String> producerRecord = new ProducerRecord<>(topic, key, value);

            // Send data - asynchronous
            // Executes everytime a record is successfully sent or an exception is thrown
            producer.send(producerRecord, (recordMetadata, exception) -> {
                if (exception == null) {
                    logger.info("Received new metadata: \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + "\n" +
                            "Offset: " + recordMetadata.offset() + "\n" +
                            "Timestamp: " + recordMetadata.timestamp() + "\n");
                } else {
                    logger.error("Error while producing", exception);
                }
            });
        }

        // Flush data
        producer.flush();

        // Flush and close Producer
        producer.close();
    }
}
