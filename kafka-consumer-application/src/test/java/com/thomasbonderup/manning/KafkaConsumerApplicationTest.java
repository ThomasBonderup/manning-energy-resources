package com.thomasbonderup.manning;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.MockConsumer;
import org.apache.kafka.clients.consumer.OffsetResetStrategy;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.internals.Topic;
import org.junit.Test;

import java.util.*;

public class KafkaConsumerApplicationTest {

    /*private final static String TEST_CONFIG_FILE = "configuration/test.properties";

    @Test
    public void consumerTest() throws Exception {
         final Properties testConsumerProps = KafkaConsumerApplication.loadProperties(TEST_CONFIG_FILE);
         final String topic = testConsumerProps.getProperty("input.topic.name");
         final TopicPartition topicPartition = new TopicPartition(topic, 0);
         final MockConsumer<String, Device> mockConsumer = new MockConsumer<>(OffsetResetStrategy.EARLIEST);

         final KafkaConsumerApplication consumerApplication = new KafkaConsumerApplication(mockConsumer);

         new Thread(() -> consumerApplication.runConsume(testConsumerProps)).start();
         Thread.sleep(500);
         addTopicPartitionsAssignmentAndAddConsumerRecord(topic, mockConsumer, topicPartition);
         Thread.sleep(500);
         consumerApplication.shutdown();
    }

    private void addTopicPartitionsAssignmentAndAddConsumerRecord(final String topic,
                                   final MockConsumer<String, Device> mockConsumer,
                                   final TopicPartition topicPartition) {

        final Map<TopicPartition, Long> beginningOffsets = new HashMap<>();
        beginningOffsets.put(topicPartition, 0L);
        mockConsumer.rebalance(Collections.singletonList(topicPartition));
        mockConsumer.updateBeginningOffsets(beginningOffsets);
        addConsumerRecords(mockConsumer, topic);
    }

    private void addConsumerRecords(final MockConsumer<String, Device> mockConsumer, final String topic) {
        *//*Alert alert1 = new Alert(12345L, Calendar.getInstance().getTimeInMillis(), alert_status.Critical);
        Alert alert2 = new Alert(12345L, Calendar.getInstance().getTimeInMillis(), alert_status.Critical);
        Alert alert3 = new Alert(12345L, Calendar.getInstance().getTimeInMillis(), alert_status.Critical);
        mockConsumer.addRecord(new ConsumerRecord(topic, 0, 0, 1, alert1));
        mockConsumer.addRecord(new ConsumerRecord(topic, 0, 1, 2, alert2));
        mockConsumer.addRecord(new ConsumerRecord(topic, 0, 2, 3, alert3));*//*
    }*/
}
