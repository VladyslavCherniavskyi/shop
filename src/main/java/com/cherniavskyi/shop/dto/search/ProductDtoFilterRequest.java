package com.cherniavskyi.shop.dto.search;

public record ProductDtoFilterRequest(
        Integer genderId,
        Integer categoryId,
        Long brandId,
        Long colorId,
        Integer sizeId
) {
}