package com.cherniavskyi.shop.repository.file.impl.cloud.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cherniavskyi.shop.dto.file.FileRequest;
import com.cherniavskyi.shop.exception.FileDeletionException;
import com.cherniavskyi.shop.exception.MultipartConvertException;
import com.cherniavskyi.shop.repository.file.FileStorageRepository;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Profile("!local")
@RequiredArgsConstructor
public abstract class AbstractS3StorageRepository implements FileStorageRepository {

    private final String bucketName;
    private final AmazonS3 amazonS3;

    @Override
    public File upload(FileRequest request) {
        var file = convert(request);
        amazonS3.putObject(
                new PutObjectRequest(
                        bucketName,
                        request.name(),
                        file
                ));
        return file;
    }

    @Override
    public Resource download(String name) {
        return Try.of(() -> {
                    var s3Object = amazonS3.getObject(bucketName, name);
                    var inputStream = s3Object.getObjectContent();
                    return new InputStreamResource(inputStream);
                }
        ).get();
    }

    @Override
    public String delete(String name) {
        return Try.of(() -> {
                    amazonS3.deleteObject(bucketName, name);
                    return String.format("Object with key '%s' deleted successfully.", name);
                }
        ).getOrElseThrow(ex ->
                new FileDeletionException("Delete a file exception ", ex)
        );
    }

    private File convert(FileRequest request) {
        var file = new File(request.name());
        return Try.of(() -> {
                    Files.copy(
                            request.file().getInputStream(),
                            file.toPath(),
                            StandardCopyOption.REPLACE_EXISTING
                    );
                    return file;
                }
        ).getOrElseThrow(throwable ->
                new MultipartConvertException("Error converting MultipartFile to File", throwable)
        );
    }
}
