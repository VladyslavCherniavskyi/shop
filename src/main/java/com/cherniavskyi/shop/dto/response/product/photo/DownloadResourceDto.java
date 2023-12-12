package com.cherniavskyi.shop.dto.response.product.photo;

import org.springframework.core.io.Resource;

public record DownloadResourceDto(
        String photoName,
        Resource resource
) {
}
