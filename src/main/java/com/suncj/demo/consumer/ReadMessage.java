package com.suncj.demo.consumer;

import java.util.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import static com.suncj.demo.consumer.Config.BOOTSTRAP_SERVERS_CONFIG;
import static com.suncj.demo.consumer.Config.GROUP_ID_CONFIG;

public class ReadMessage {
    public List readMessage(int count) {
        int maxMessagesToReturn = count;

        Properties props = new Properties();
        props.put(BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        props.put(GROUP_ID_CONFIG, "test");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        List<String> messagesFromKafka = new ArrayList<>();

        Set<TopicPartition> partitions = new HashSet<>();
        TopicPartition actualTopicPartition = new TopicPartition("Data_Delivery", 0);
        consumer.assign(Arrays.asList(actualTopicPartition));

        partitions.add(actualTopicPartition);

        long actualEndOffset = consumer.endOffsets(partitions).get(actualTopicPartition);
        long recentMessagesStartPosition = actualEndOffset - maxMessagesToReturn;

        consumer.seek(actualTopicPartition, recentMessagesStartPosition);

        ConsumerRecords<String, String> records = consumer.poll(1000);
        for (ConsumerRecord<String, String> record : records) {
            if(record.value() != null) {
                        messagesFromKafka.add(record.value());
                    }
                }
        consumer.close();
        return messagesFromKafka;
    }
}