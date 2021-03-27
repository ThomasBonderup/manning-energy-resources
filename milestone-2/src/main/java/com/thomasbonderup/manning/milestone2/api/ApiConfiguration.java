package com.thomasbonderup.manning.milestone2.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class ApiConfiguration extends com.thomasbonderup.manning.milestone1.api.ApiConfiguration {
    private int maxBodySize = 1024 * 1024;

    @JsonProperty("maxBodySize")
    public int getMaxBodySize() { return maxBodySize; }

    @JsonProperty("maxBodySize")
    public void setMaxBodySize(int MaxBodySize) { maxBodySize = MaxBodySize; }

    @NotNull
    private S3Configuration s3;

    @JsonProperty("s3")
    public S3Configuration getS3() { return s3; }

    @JsonProperty("s3")
    public void setS3(S3Configuration S3) { s3 = S3; }

}