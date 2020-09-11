package com.github.escalhao.kafka.tutorial1;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ConsumerDemo {
    private static final String KAFKA_ADDRESS = "127.0.0.1:9092";
    private static final String GROUP_ID = "consumer-java-4";

    public static void main(String[] args) {
        new ConsumerDemo().run();
    }

    private ConsumerDemo() {
    }

    private void run() {
        String topic = "first_topic";
        Logger logger = LoggerFactory.getLogger(ConsumerDemo.class.getName());

        // Create Consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_ADDRESS);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        CountDownLatch latch = new CountDownLatch(1);

        ConsumerRunnable runnable = new ConsumerRunnable(latch, properties, topic);

        Thread thread = new Thread(runnable);

        logger.info("Starting thread");
        thread.start();

        Runtime.getRuntime().addShutdownHook(new Thread( () -> {
            logger.info("Got a shutdown hook");
            runnable.shutdown();

            try {
                latch.await();
            } catch (InterruptedException e) {
                logger.error("Application got an error: ", e);
            } finally {
                logger.info("Application exited");
            }
        }

        ));

        try {
            latch.await();
        } catch (InterruptedException e) {
            logger.error("Application got interrupted: ", e);
        } finally {
            logger.info("Application is closed");
        }
    }

    public class ConsumerRunnable implements Runnable {

        private CountDownLatch latch;
        private KafkaConsumer<String,String> consumer;
        private Logger logger = LoggerFactory.getLogger(ConsumerRunnable.class.getName());

        public ConsumerRunnable(CountDownLatch latch, Properties properties, String topic) {
            this.latch = latch;

            // Create Consumer
            this.consumer = new KafkaConsumer<>(properties);

            // Subscribe Consumer to Topics
            // If you have more than one topic use consumer.subscribe(Arrays.asList("topic_1", "topic_2", "topic_n"...))
            this.consumer.subscribe(Collections.singleton(topic));
        }

        @Override
        public void run() {
            try {
                while (true) {
                    // Poll for new data from topic
                    ConsumerRecords<String, String> records = this.consumer.poll(Duration.ofMillis(100));

                    for (ConsumerRecord<String, String> record : records) {
                        this.logger.info("Key: " + record.key());
                        this.logger.info("Value: " + record.value() + "\n");
                    }
                }
            } catch (WakeupException ex){
                this.logger.info("Received a Shutdown call");
            } finally {
                this.consumer.close();
                latch.countDown();
                logger.info("Consumer closed");
            }
        }

        private void shutdown() {
            this.consumer.wakeup();
        }
    }
}
