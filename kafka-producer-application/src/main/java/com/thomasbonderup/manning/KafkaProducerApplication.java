package com.thomasbonderup.manning;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

public class KafkaProducerApplication extends Application<KafkaProducerConfiguration> {

  public static void main(String[] args) throws Exception {
    new KafkaProducerApplication().run(args);
  }

  @Override
  public String getName() {
    return "hello-world";
  }

  public void initialize(Bootstrap<KafkaProducerConfiguration> bootstrap) {

  }

  public void run(KafkaProducerConfiguration configuration, Environment environment) {
    final KafkaProducerResource resource = new KafkaProducerResource(
            configuration.getTemplate(),
            configuration.getDefaultName()
    );

    final Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:29092");
    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    props.put("value.serializer", "io.confluent.kafka.serializers.KafkaAvroSerializer");
    props.put("schema.registry.url", "http://localhost:8090");
    final String topic = "output-topic";

    Producer<String, Device> producer = new KafkaProducer<>(props);
    final ProduceMessages producerApp = new ProduceMessages(producer, topic,
            configuration.getTemplate(), configuration.getDefaultName());

    final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("template", healthCheck);
    environment.jersey().register(resource);
    environment.jersey().register(producerApp);
  }
}