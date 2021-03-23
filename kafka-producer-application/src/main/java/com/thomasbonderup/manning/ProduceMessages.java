package com.thomasbonderup.manning;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.Future;

@Path("/")
public class ProduceMessages {

    private final String template;

    private final String defaultName;
    private final Producer<String, Device> producer;
    final String outTopic;

    public ProduceMessages(final Producer<String, Device> producer,
                           final String topic,
                           String template,
                           String defaultName) {
        this.producer = producer;
        outTopic = topic;
        this.template = template;
        this.defaultName = defaultName;
    }

    @POST
    @Path("/device/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Future<RecordMetadata> produce(Device device, @PathParam("id") String id) {
        device.setDeviceId(id);
        device.setTime(Calendar.getInstance().getTimeInMillis());
        // System.out.println(device.toString());
        ProducerRecord<String, Device> producerRecord =
                new ProducerRecord<String, Device>(outTopic, device.getDeviceId().toString(), device);
        return producer.send(producerRecord, new AlertCallback());
    }

    public void shutdown() { producer.close(); }
    public static Properties loadProperties(String fileName) throws IOException {
        final Properties envProps = new Properties();
        final FileInputStream input = new FileInputStream(fileName);
        envProps.load(input);
        input.close();
        return envProps;
    }
/*
    public static void main(String[] args) throws Exception {


        if (args.length < 1) {
            throw new IllegalStateException(
                    "This program takes one argument: the path to an environment configuration file");
        }

         final Properties props = ProduceMessages.loadProperties(args[0]);
         final String topic = props.getProperty("output.topic.name");
         Producer<String, Device> producer = new KafkaProducer<>(props);
         final ProduceMessages producerApp = new ProduceMessages(producer, topic, template);

         Attach shutdown handler to catch Control-C.
         Runtime.getRuntime().addShutdownHook(new Thread(producerApp::shutdown));

        Thread.currentThread().join();
    Alert alert = new Alert();
    alert.setSensorId(1245L);
    alert.setTime(Calendar.getInstance().getTimeInMillis());
    alert.setStatus(alert_status.Critical);
    System.out.println(alert.toString());
    producerApp.produce(alert);

         producerApp.shutdown();
    }*/

}