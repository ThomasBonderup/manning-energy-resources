package com.thomasbonderup.manning.milestone2.api;

import com.thomasbonderup.manning.milestone1.api.CloseableManaged;
import com.thomasbonderup.manning.milestone1.api.DeviceDAO;
import com.thomasbonderup.manning.milestone2.api.DeviceEndpoint;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jdbi.v3.core.Jdbi;

import java.util.Properties;

public class ApiServer extends Application<ApiConfiguration> {
    @Override
    public void run(ApiConfiguration apiConfiguration, Environment environment) throws Exception {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, apiConfiguration.getDataSourceFactory(),
                "device-db");

        KafkaProducer kafkaProducer = createProducer(apiConfiguration);
        environment.lifecycle().manage(new CloseableManaged(kafkaProducer));

        environment.jersey().register(new DeviceEndpoint(kafkaProducer,
                apiConfiguration.getTopic(), jdbi.onDemand(DeviceDAO.class),
                apiConfiguration.getDeviceTable(),
                apiConfiguration.getMaxBodySize(),
                new S3(apiConfiguration.getS3())));
    }

    private KafkaProducer createProducer(ApiConfiguration configuration) {
        Properties props = new Properties();

        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());

        props.putAll(configuration.getKafka());
        return new KafkaProducer(props);
    }

    public static void main(String[] args) throws Exception {
        new ApiServer().run(args);
    }
}
