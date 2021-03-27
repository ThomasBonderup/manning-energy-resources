package com.thomasbonderup.manning.milestone2.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class S3Configuration {
    private String endpoint;

    private String bucket;

    @JsonProperty("endpoint")
    public String getEndpoint() { return endpoint; }

    @JsonProperty("endpoint")
    public void setEndpoint(String Endpoint) { endpoint = Endpoint; }

    @JsonProperty("bucket")
    public String getBucket() { return bucket; }

    @JsonProperty("bucket")
    public void setBucket(String Bucket) { bucket = Bucket; }
}