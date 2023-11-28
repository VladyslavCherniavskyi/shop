package com.cherniavskyi.shop.dto.response.product;

public record BrandDtoResponse(
        Long id,
        String name,
        String country,
        String description
) {
}
