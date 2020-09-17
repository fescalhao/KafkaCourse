package com.github.escalhao.kafka.tutorial2;

import com.google.gson.JsonParser;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class StreamsFilterTweets {
    public static void main(String[] args) {
        final String kafkaAddress = "127.0.0.1:9092";
        final String appId = "twitter-consumer";
        final String twitterTopic = "twitter.tweets";
        final String importantTwitterTopic = "important.twitter.tweets";

        Properties props = new Properties();
        props.setProperty(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaAddress);
        props.setProperty(StreamsConfig.APPLICATION_ID_CONFIG, appId);
        props.setProperty(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());
        props.setProperty(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.StringSerde.class.getName());

        StreamsBuilder streamsBuilder = new StreamsBuilder();
        KStream<String, String> inputTopic = streamsBuilder.stream(twitterTopic);
        KStream<String, String> filteredInputTopic = inputTopic.filter(
                (k, jsonTweet) -> getUserFollowersCount(jsonTweet) > 1000
        );
        filteredInputTopic.to(importantTwitterTopic);

        KafkaStreams streams = new KafkaStreams(streamsBuilder.build(), props);
        streams.start();
    }

    private static Integer getUserFollowersCount(String tweet) {
        try {
            return JsonParser.parseString(tweet)
                    .getAsJsonObject()
                    .get("user")
                    .getAsJsonObject()
                    .get("followers_count")
                    .getAsInt();
        } catch (NullPointerException e) {
            return 0;
        }
    }
}
