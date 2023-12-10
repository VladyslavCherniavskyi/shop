package com.cherniavskyi.shop.dto.response.product.photo;

public record PhotoDtoResponse(
        String name,
        String url,
        String type,
        Long size
) {
}
