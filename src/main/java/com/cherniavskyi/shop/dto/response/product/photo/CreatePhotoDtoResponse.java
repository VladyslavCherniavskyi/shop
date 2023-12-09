package com.cherniavskyi.shop.dto.response.product.photo;

public record CreatePhotoDtoResponse(
        String name,
        String url,
        String type,
        Long size
) {
}
