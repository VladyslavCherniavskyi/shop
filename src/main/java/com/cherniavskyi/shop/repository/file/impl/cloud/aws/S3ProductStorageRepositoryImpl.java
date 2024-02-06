package com.cherniavskyi.shop.repository.file.impl.cloud.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.cherniavskyi.shop.repository.file.ProductFileStorageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!local")
public class S3ProductStorageRepositoryImpl extends AbstractS3StorageRepository implements ProductFileStorageRepository {

    public S3ProductStorageRepositoryImpl(
            @Value("${cloud.aws.productBucketName}")
            String bucketName,
            AmazonS3 amazonS3
    ) {
        super(bucketName, amazonS3);
    }
}
