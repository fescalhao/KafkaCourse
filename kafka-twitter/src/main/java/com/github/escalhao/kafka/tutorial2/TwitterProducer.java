package com.github.escalhao.kafka.tutorial2;

import com.google.common.collect.Lists;
import com.twitter.hbc.ClientBuilder;
import com.twitter.hbc.core.Client;
import com.twitter.hbc.core.Constants;
import com.twitter.hbc.core.Hosts;
import com.twitter.hbc.core.HttpHosts;
import com.twitter.hbc.core.endpoint.StatusesFilterEndpoint;
import com.twitter.hbc.core.processor.StringDelimitedProcessor;
import com.twitter.hbc.httpclient.auth.Authentication;
import com.twitter.hbc.httpclient.auth.OAuth1;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class TwitterProducer {

    Logger logger = LoggerFactory.getLogger(TwitterProducer.class.getName());

    public TwitterProducer(){

    }

    public static void main(String[] args) {
        new TwitterProducer().run();
    }

    private void run() {
        BlockingQueue<String> msgQueue = new LinkedBlockingQueue<>(1000);

        Client client = createTwitterClient(msgQueue);
        client.connect();

        KafkaProducer<String,String> producer = createKafkaProducer();

        while (!client.isDone()) {
            String msg = null;
            try {
                msg = msgQueue.poll(5, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                logger.error("Error while polling messages: " + e);
                client.stop();
            }

            if (msg != null) {
                producer.send(new ProducerRecord<>("twitter.tweets", null, msg), new Callback() {
                    @Override
                    public void onCompletion(RecordMetadata metadata, Exception exception) {
                        if (exception != null) {
                            logger.error("There was an exception sending data to Kafka: ", exception);
                        }
                    }
                });
            }
            producer.flush();
        }
        producer.close();
    }

    private Client createTwitterClient(BlockingQueue<String> msgQueue) {

        Hosts hosebirdHosts = new HttpHosts(Constants.STREAM_HOST);
        StatusesFilterEndpoint hosebirdEndpoint = new StatusesFilterEndpoint();

        List<String> terms = Lists.newArrayList("music");
        hosebirdEndpoint.trackTerms(terms);
//        List<Long> follows = Lists.newArrayList(2390025414L, 872060637559611392L);
//        hosebirdEndpoint.followings(follows);

        // These secrets should be read from a config file
        String consumerKey = "GGk3PJgjeEYCEcdNtL28wK58f";
        String consumerSecret = "7q2JGc4Yg5vezzhp9BT5wHATFRPOZezfc5jmA2BVr7xTaW28uP";
        String token = "2390025414-v6tk0F9lBHwUHuyJC8tmjJEFT1MVWiNHuAvEtaD";
        String secret = "HD2VWok2SSqmluyB03R7pCwLRPe7sXlvRysVltm00zB3A";

        Authentication hosebirdAuth = new OAuth1(consumerKey, consumerSecret, token, secret);

        ClientBuilder builder = new ClientBuilder()
                .name("Hosebird-Client-01")
                .hosts(hosebirdHosts)
                .authentication(hosebirdAuth)
                .endpoint(hosebirdEndpoint)
                .processor(new StringDelimitedProcessor(msgQueue));

        return builder.build();
    }

    private KafkaProducer<String, String> createKafkaProducer() {
        final String kafkaAddress = "127.0.0.1:9092";

        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
        properties.setProperty(ProducerConfig.MAX_IN_FLIGHT_REQUESTS_PER_CONNECTION, "5");
        properties.setProperty(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "20");
        properties.setProperty(ProducerConfig.BATCH_SIZE_CONFIG, Integer.toString(32*1024));
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, Integer.toString(Integer.MAX_VALUE));

        KafkaProducer<String,String> producer = new KafkaProducer<>(properties);

        return producer;
    }
}
