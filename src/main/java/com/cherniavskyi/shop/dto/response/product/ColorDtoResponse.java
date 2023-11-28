package com.cherniavskyi.shop.dto.response.product;

public record ColorDtoResponse(
        Long id,
        String name,
        String htmlCode,
        Integer redComponent
) {
}
