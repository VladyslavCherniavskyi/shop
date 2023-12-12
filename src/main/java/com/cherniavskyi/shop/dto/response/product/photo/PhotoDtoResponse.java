package com.cherniavskyi.shop.dto.response.product.photo;

import java.util.UUID;

public record PhotoDtoResponse(
        UUID id,
        String name,
        String url,
        String type,
        Long size,
        Long productId
) {
}
