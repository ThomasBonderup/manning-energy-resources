package com.thomasbonderup.manning;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

public class AlertCallback implements Callback {
    @Override
    public void onCompletion(RecordMetadata metadata, Exception e) {
        if (e != null) {
            System.out.printf("Error sending message: " + "offset = %d, topic = %s, timestamp = %Tc %n",
                    metadata.offset(), metadata.topic(), metadata.timestamp());
        } else {
            System.out.println("Record written to offset " + metadata.offset() + " timestamp " + metadata.timestamp());
        }
    }
}
