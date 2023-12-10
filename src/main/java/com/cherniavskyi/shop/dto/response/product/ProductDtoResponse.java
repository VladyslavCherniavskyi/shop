package com.cherniavskyi.shop.dto.response.product;

import com.cherniavskyi.shop.dto.response.product.photo.PhotoDtoResponse;

import java.math.BigDecimal;
import java.util.Set;

public record ProductDtoResponse(
        Long id,
        String name,
        String description,
        Set<PhotoDtoResponse> photos,
        BigDecimal price,
        Integer stockQuantity,
        Set<CategoryDtoResponse> categories,
        Set<SizeDtoResponse> sizes,
        Set<BrandDtoResponse> brands,
        Set<ColorDtoResponse> colors,
        Set<GenderDtoResponse> genders
) {
}