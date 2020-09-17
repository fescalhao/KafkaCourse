package com.github.escalhao.kafka.tutorial2;

import com.google.gson.JsonParser;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ElasticSearchConsumer {
    public ElasticSearchConsumer() {

    }

    public static void main(String[] args) throws IOException {
        Logger logger = LoggerFactory.getLogger(ElasticSearchConsumer.class.getName());

        final String index = "twitter";
        final String twitterTopic = "twitter.tweets";

        RestHighLevelClient client = createElasticSearchClient();

        try (KafkaConsumer<String, String> consumer = createTwitterKafkaConsumer(twitterTopic)) {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
                BulkRequest bulkRequest = new BulkRequest();
                Integer recordsCount = records.count();

                logger.info(recordsCount + " received");
                for (ConsumerRecord<String, String> record : records) {
                    String tweetId = getTweetId(record.value());

                    IndexRequest indexRequest = new IndexRequest(index)
                    .id(tweetId)
                    .source(record.value(), XContentType.JSON);

                    bulkRequest.add(indexRequest);
                    Thread.sleep(100);
                }

                if (recordsCount > 0) {
                    BulkResponse bulkItemResponses = client.bulk(bulkRequest, RequestOptions.DEFAULT);
                    logger.info("Committing offset");
                    consumer.commitSync();
                    logger.info("Offset committed");
                }


            }
        } catch (WakeupException ex) {
            logger.info("Received a Shutdown call");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            logger.warn("Skipping bad data: ", e);
        } finally {
            logger.info("Consumer closed");
        }

        client.close();
    }

    public static RestHighLevelClient createElasticSearchClient() {
        final String hostname = "fescalhao-kafka-cour-6046074821.us-east-1.bonsaisearch.net";
        final String username = "pt761nxxtj";
        final String password = "wgyo3poe0w";

        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));

        RestClientBuilder clientBuilder = RestClient.builder(
                new HttpHost(hostname, 443, "https"))
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider));

        return new RestHighLevelClient(clientBuilder);
    }

    public static KafkaConsumer<String, String> createTwitterKafkaConsumer(String topic){
        final String kafkaAddress = "127.0.0.1:9092";
        final String grouId = "twitter-consumer";


        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, grouId);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        properties.setProperty(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "20");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton(topic));

        return consumer;
    }

    private static String getTweetId(String tweet) {
        return JsonParser.parseString(tweet)
                .getAsJsonObject()
                .get("id_str")
                .getAsString();
    }
}
