package com.cherniavskyi.shop.dto.response.product;

import java.math.BigDecimal;

public record ProductDtoResponse(
        Long id,
        String name,
        String description,
        String referenceImage,
        BigDecimal price,
        Integer stockQuantity,
        Integer categoryId
) {
}