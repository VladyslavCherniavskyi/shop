package com.cherniavskyi.shop.dto.query;

public record FilterDtoQuery(
        Integer genderId,
        Integer categoryId,
        Long brandId,
        Long colorId,
        Integer sizeId
) {
}
