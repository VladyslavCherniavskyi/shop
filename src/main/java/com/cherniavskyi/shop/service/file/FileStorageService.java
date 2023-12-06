package com.cherniavskyi.shop.service.file;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface FileStorageService {

    File upload(MultipartFile file);

    Resource download(String name);

    String delete(String name);

}
