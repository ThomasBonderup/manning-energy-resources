package com.thomasbonderup.manning;

import com.thomasbonderup.manning.common.StreamConfiguration;
import com.thomasbonderup.manning.stream.BytesToJson;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConsumeMessages {
/*
    private final String template;

    private final String defaultName;

    private final String table;

    // private volatile boolean keepConsuming = true;
    // private final Consumer<String, Device> consumer;

    private StreamConfiguration conf;

    public ConsumeMessages(final Consumer<String, Device> consumer, String template, String defaultName, String table) {
        this.consumer = consumer;
        this.template = template;
        this.defaultName = defaultName;
        this.table = table;
    }

    *//*public void runConsume(final Properties consumerProps) {
        try {
            consumer.subscribe(Collections.singleton(consumerProps.getProperty("input.topic.name")));
            while (keepConsuming) {
                final ConsumerRecords<String, Device> consumerRecords = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, Device> record : consumerRecords) {
                    System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
                }
            }
        } finally {
            consumer.close();
        }
    }*//*

    public void buildStream() {
        BytesToJson toJson = new BytesToJson();
        StreamsBuilder builder = new StreamsBuilder();
        builder.stream(conf.getSource(),
                Consumed.with(Serdes.String(), valueSpecificAvroSerde))
                .mapValues(rawRecord -> {
                    return toJson.apply(rawRecord.getBody());
                })
                .flatMapValues(i -> i)
                .foreach((uuid, value) -> {
                    long charging = Long.valueOf(value.get("charging").toString());
                    db.setDeviceState(table, uuid, charging > 0);
                });
    }

    public void createDatabase() {

    }


    //public void shutdown() { keepConsuming = false; }

    public static Properties loadProperties(String fileName) throws IOException {
        final Properties props = new Properties();
        final FileInputStream input = new FileInputStream(fileName);
        props.load(input);
        input.close();
        return props;
    }*/
}
