package com.cherniavskyi.shop.repository.file.impl.local;

import com.cherniavskyi.shop.dto.file.FileRequest;
import com.cherniavskyi.shop.exception.FileDeleteException;
import com.cherniavskyi.shop.repository.file.FileStorageRepository;
import com.cherniavskyi.shop.util.PathUtils;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.File;
import java.nio.file.Files;

@Profile("local")
@RequiredArgsConstructor
public abstract class AbstractLocalStorageRepository implements FileStorageRepository {

    private final String directory;

    @Override
    public File upload(FileRequest request) {
        var filePath = PathUtils.getAbsolutePath(directory, request.name()).toString();
        return Try.of(() -> {
                    request.file().transferTo(new File(filePath));
                    return new File(filePath);
                }
        ).get();
    }

    @Override
    public Resource download(String name) {
        var filePath = PathUtils.getAbsolutePath(directory, name).toUri();
        return Try.of(() -> new UrlResource(filePath)
        ).get();
    }

    @Override
    public String delete(String name) {
        var filePath = PathUtils.getAbsolutePath(directory, name);
        return Try.of(() -> {
                    Files.delete(filePath);
                    return String.format("File named: %s deleted successfully", name);
                }
        ).getOrElseThrow(ex ->
                new FileDeleteException("Delete a file exception ", ex)
        );
    }
}
