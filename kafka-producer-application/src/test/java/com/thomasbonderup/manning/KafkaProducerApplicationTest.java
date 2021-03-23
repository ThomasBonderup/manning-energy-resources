package com.thomasbonderup.manning;

import org.apache.kafka.clients.producer.MockProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.streams.KeyValue;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class KafkaProducerApplicationTest {
    private final static String TEST_CONFIG_FILE = "configuration/test.properties";

    @Test
    public void testProduce() throws IOException {
//        final MockProducer<Long, Alert> mockProducer = new MockProducer<>();
//        final Properties props = KafkaProducerApplication.loadProperties(TEST_CONFIG_FILE);
//        final String topic = props.getProperty("output.topic.name");
//        final KafkaProducerApplication producerApp = new KafkaProducerApplication(mockProducer, topic);
//
//        Alert alert1 = new Alert(12345L, Calendar.getInstance().getTimeInMillis(), alert_status.Critical);
//        Alert alert2 = new Alert(12346L, Calendar.getInstance().getTimeInMillis(), alert_status.Critical);
//        Alert alert3 = new Alert(12347L, Calendar.getInstance().getTimeInMillis(), alert_status.Critical);
//
//        final List<Alert> records = Arrays.asList(alert1, alert2, alert3);
//
//        records.forEach(producerApp::produce);
//
//        final List<KeyValue<Long, Alert>> expectedList = Arrays.asList(
//                KeyValue.pair(12345L, alert1),
//                KeyValue.pair(12346L, alert2),
//                KeyValue.pair(12347L, alert3));
//
//        final List<KeyValue<Long, Alert>> actualList = mockProducer.history().stream().map(this::toKeyValue).collect(Collectors.toList());
//
//        assertThat(actualList, equalTo(expectedList));
//        producerApp.shutdown();
    }

    private KeyValue<Long, Alert> toKeyValue(final ProducerRecord<Long, Alert> producerRecord) {
        return KeyValue.pair(producerRecord.key(), producerRecord.value());
    }
}