package com.thomasbonderup.manning.common;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class App <T extends StreamConfiguration> extends Application<T> {

  @Override
  public void run(T configuration, Environment environment) throws Exception {

  }
  /*public static void main(String[] args) throws Exception {
    new App().run(args);
    *//*if (args.length < 1) {
      throw new IllegalStateException(
              "This program takes one argument: the path to an environment configuration file");
    }

    final Properties consumerAppProps = KafkaConsumerApplication.loadProperties(args[0]);
    final Consumer<String, Device> consumer = new KafkaConsumer<>(consumerAppProps);
    final KafkaConsumerApplication consumerApplication = new KafkaConsumerApplication(consumer);

    // Attach shutdown handler to catch Control-C.
    Runtime.getRuntime().addShutdownHook(new Thread(consumerApplication::shutdown));

    consumerApplication.runConsume(consumerAppProps);*//*
  }

  //public void shutdown() { keepConsuming = false; }

  public void initialize(Bootstrap<StreamConfiguration> boostrap) {
    //boostrap.addCommand(new ConsumerCommand());
  }*/

  /*public void run(StreamConfiguration configuration, Environment environment) {
    final Properties props = new Properties();
    props.put("bootstrap.servers", "localhost:29092");
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
    props.put("value.deserializer", "io.confluent.kafka.serializers.KafkaAvroDeserializer");
    props.put("max.poll.interval.ms", "300000");
    props.put("enable.auto.commit", "true");
    props.put("auto.offset.reset", "earliest");
    props.put("group.id", "consumer-application");
    props.put("schema.registry.url", "http://localhost:8090");
    props.put("input.topic.name", "output-topic");
    props.put("input.topic.partitions", "1");
    props.put("input.topic.replication.factor", "1");

    final Consumer<String, Device> consumer = new KafkaConsumer<String, Device>(props);
    final ConsumeMessages consumerApp = new ConsumeMessages(consumer, configuration.getTemplate(), configuration.getDefaultName());

    final TemplateHealthCheck healthCheck =
            new TemplateHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("template", healthCheck);

    final JdbiFactory factory = new JdbiFactory();
    final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
    DeviceDAO device = jdbi.onDemand(DeviceDAO.class);
    final DeviceResource resource = new DeviceResource(device);
    environment.jersey().register(resource);
    device.insert("1", 500);
    System.out.println("Got email: " + device.findDeviceById("1"));

    //Runtime.getRuntime().addShutdownHook(new Thread(consumerApp::shutdown));

    //environment.jersey().register(consumerApp);
    //consumerApp.runConsume(props);
  }*/
}