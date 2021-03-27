package com.thomasbonderup.manning.milestone2.api;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

import java.io.InputStream;
import java.time.Instant;
import java.time.format.DateTimeFormatter;

public class S3 {

    private DateTimeFormatter format = DateTimeFormatter.ofPattern("%YYYY/%MM/%DD");

    private final AmazonS3 s3;

    private final String bucket;

    public S3(AmazonS3 s3, String bucket) {
        this.s3 = s3;
        this.bucket = bucket;
    }
    public S3(S3Configuration configuration) {
        this.s3 = new AmazonS3Client();
        s3.setEndpoint(configuration.getEndpoint());
        this.bucket = configuration.getBucket();
    }

    public String put(String deviceUUID, String fileName, InputStream stream, long size) {
        Instant now = Instant.now();
        String key = String.format("%s/%s/%s", format.format(now), deviceUUID, fileName);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(size);
        PutObjectRequest request = new PutObjectRequest(bucket, key, stream, metadata);
        s3.putObject(request);
        return key;
    }

    public InputStream read(String key) { return s3.getObject(bucket, key).getObjectContent(); }
}
