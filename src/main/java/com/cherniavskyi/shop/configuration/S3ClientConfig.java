package com.cherniavskyi.shop.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3ClientConfig {

    @Value("${cloud.aws.accessKey}")
    private String accessKey;

    @Value("${cloud.aws.secretAccessKey}")
    private String secretAccessKey;

    @Value("${cloud.aws.region}")
    private String region;

    @Bean
    public AmazonS3 initS3Client() {
        var credentials = new BasicAWSCredentials(accessKey, secretAccessKey);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
    }

}
