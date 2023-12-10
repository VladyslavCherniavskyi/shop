package com.cherniavskyi.shop.dto.response.product.photo;

import org.springframework.core.io.Resource;

public record ResourceDtoDownload(
        String photoName,
        Resource resource
) {
}
