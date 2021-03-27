package com.thomasbonderup.manning.milestone2.api;

import com.thomasbonderup.manning.milestone1.api.DeviceDAO;
import com.thomasbonderup.manning.devices.raw.milestone2.RawRecord;
import org.apache.commons.io.IOUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.lang.String.format;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.APPLICATION_OCTET_STREAM;

public class DeviceEndpoint extends com.thomasbonderup.manning.milestone1.api.DeviceEndpoint{

    private final int maxSize;

    private final S3 s3;

    public DeviceEndpoint(KafkaProducer producer, String topic, DeviceDAO db, String table,
                          int maxBodySize, S3 s3) {
        super(producer, topic, db, table);
        this.maxSize = maxBodySize;
        this.s3 = s3;
    }

    @POST
    @Path("/send/{uuid")
    @Consumes({APPLICATION_OCTET_STREAM, APPLICATION_JSON})
    @Produces(APPLICATION_JSON)
    public Response send(@PathParam("uuid") String uuid, @Context HttpServletRequest request)
    throws ExecutionException, InterruptedException, IOException {
        long now = Instant.now().toEpochMilli();
        RawRecord.Builder builder = RawRecord.newBuilder()
                .setUuid(uuid)
                .setArrivalTimeMs(now);
        InputStream stream = request.getInputStream();
        byte[] max = IOUtils.toByteArray(stream, maxSize);

        if (stream.available() <= 0) {
            builder.setBody(ByteBuffer.wrap(max));
        } else {
            String path = format("%s-%d-%s", uuid, now, UUID.randomUUID());
            java.nio.file.Path tmp = Files.createTempFile("send", path);
            Files.copy(new SequenceInputStream(new ByteArrayInputStream(max), stream), tmp);
            String ref = s3.put(uuid, path, Files.newInputStream(tmp), Files.size(tmp));
            builder.setBodyReference(ref);
        }
        ProducerRecord record = new ProducerRecord(topic, uuid, builder.build());
        Future<RecordMetadata> metadata = producer.send(record);
        return Response.ok().entity(serialize(metadata.get())).build();
    }
}
