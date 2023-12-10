package com.cherniavskyi.shop.dto.request.product.create;

import com.cherniavskyi.shop.dto.response.product.*;
import com.cherniavskyi.shop.dto.response.product.photo.PhotoDtoResponse;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

public record ProductDtoCreateRequest(

        @NotBlank(message = "Name cannot be empty")
        String name,

        String description,

        Set<PhotoDtoResponse> photos,

        @DecimalMin(value = "0.0", message = "Price should be greater than or equal to 0")
        BigDecimal price,

        @NotNull(message = "StockQuantity cannot be null")
        @Positive(message = "StockQuantity should be greater than 0")
        Integer stockQuantity,

        @NotEmpty(message = "Categories cannot be empty")
        Set<CategoryDtoResponse> categories,

        @NotEmpty(message = "Sizes cannot be empty")
        Set<SizeDtoResponse> sizes,

        @NotEmpty(message = "Brands cannot be empty")
        Set<BrandDtoResponse> brands,

        @NotEmpty(message = "Colors cannot be empty")
        Set<ColorDtoResponse> colors,

        @NotEmpty(message = "Genders cannot be empty")
        Set<GenderDtoResponse> genders

) {
}
