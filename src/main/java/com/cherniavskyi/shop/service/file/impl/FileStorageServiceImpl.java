package com.cherniavskyi.shop.service.file.impl;

import com.cherniavskyi.shop.service.file.FileStorageService;
import com.cherniavskyi.shop.util.PathUtils;
import io.vavr.control.Try;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.UUID;

@Service
@Transactional
public class FileStorageServiceImpl implements FileStorageService {
    @Override
    public File upload(MultipartFile file) {
        var fileName = UUID.randomUUID().toString();
        var filePath = PathUtils.getAbsolutePath(fileName).toString();
        return Try.of(() -> {
                    file.transferTo(new File(filePath));
                    return new File(filePath);
                }
        ).get();
    }

    @Override
    public Resource download(String name) {
        var filePath = PathUtils.getAbsolutePath(name).toUri();
        return Try.of(() -> new UrlResource(filePath)
        ).get();
    }

    @Override
    public String delete(String name) {
        var filePath = PathUtils.getAbsolutePath(name);
        return Try.of(() -> {
                    Files.delete(filePath);
                    return String.format("File named: %s deleted successfully", name);
                }
        ).get();
    }
}
