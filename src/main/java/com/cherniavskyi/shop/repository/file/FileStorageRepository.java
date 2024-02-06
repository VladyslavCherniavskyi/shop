package com.cherniavskyi.shop.repository.file;

import com.cherniavskyi.shop.dto.file.FileRequest;
import org.springframework.core.io.Resource;

import java.io.File;

public interface FileStorageRepository {

    File upload(FileRequest request);

    Resource download(String name);

    String delete(String name);

}
