package com.thomasbonderup.manning.api;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.jdbi.v3.core.Jdbi;

import java.util.Properties;

public class ApiServer extends Application<ApiConfiguration> {

    @Override
    public void initialize(Bootstrap<ApiConfiguration> bootstrap) {
        bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    }

    public void run(ApiConfiguration conf, Environment environment) throws Exception {
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, conf.getDataSourceFactory(), "device-db");

        KafkaProducer producer = createProducer(conf);
        environment.lifecycle().manage(new CloseableManaged(producer));

        environment.jersey().register(new DeviceEndpoint(producer, conf.getTopic(),
                jdbi.onDemand(DeviceDAO.class), conf.getDeviceTable()));

        environment.jersey().setUrlPattern("/api");
    }

    private KafkaProducer createProducer(ApiConfiguration conf) {
        Properties props = new Properties();

        props.put(ProducerConfig.ACKS_CONFIG, "1");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());

        props.putAll(conf.getKafka());
        return new KafkaProducer(props);
    }

    public static void main(String[] args) throws Exception {
        new ApiServer().run(args);
    }
}
