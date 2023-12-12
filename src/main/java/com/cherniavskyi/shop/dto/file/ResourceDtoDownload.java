package com.cherniavskyi.shop.dto.file;

import org.springframework.core.io.Resource;

public record ResourceDtoDownload(
        String photoName,
        Resource resource
) {
}
