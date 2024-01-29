package com.cherniavskyi.shop.repository.file.impl.cloud.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.cherniavskyi.shop.repository.file.UserFileStorageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("!local")
public class S3UserStorageRepositoryImpl extends AbstractS3StorageRepository implements UserFileStorageRepository {

    public S3UserStorageRepositoryImpl(
            @Value("${cloud.aws.userBucketName}")
            String bucketName,
            AmazonS3 amazonS3
    ) {
        super(bucketName, amazonS3);
    }

}
