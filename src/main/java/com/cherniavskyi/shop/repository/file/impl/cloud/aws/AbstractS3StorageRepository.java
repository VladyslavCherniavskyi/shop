package com.cherniavskyi.shop.repository.file.impl.cloud.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.cherniavskyi.shop.exception.FileDeletionException;
import com.cherniavskyi.shop.exception.MultipartConvertException;
import com.cherniavskyi.shop.repository.file.FileStorageRepository;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Profile("!local")
@RequiredArgsConstructor
public abstract class AbstractS3StorageRepository implements FileStorageRepository {

    private final String bucketName;
    private final AmazonS3 amazonS3;

    @Override
    public File upload(MultipartFile file) {
        var fileName = UUID.randomUUID().toString();
        var convertFile = convert(file);
        amazonS3.putObject(
                new PutObjectRequest(
                        bucketName,
                        fileName,
                        convertFile
                ));
        return Try.of(() -> {
            Files.delete(convertFile.toPath());
            return convertFile;
        }).getOrElseThrow(ex ->
                new FileDeletionException("Delete a file exception ", ex)
        );
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
        return null;
    }

    private File convert(MultipartFile file) {
        var convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        return Try.of(() -> {
                    Files.copy(
                            file.getInputStream(),
                            convertedFile.toPath(),
                            StandardCopyOption.REPLACE_EXISTING
                    );
                    return convertedFile;
                }
        ).getOrElseThrow(throwable ->
                new MultipartConvertException("Error converting MultipartFile to File", throwable)
        );
    }
}
