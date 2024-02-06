package com.cherniavskyi.shop.dto.file;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record FileRequest(
        @NotNull(message = "MultipartFile cannot be null") MultipartFile file,
        @NotBlank(message = "Name cannot be empty") String name
) {
}
