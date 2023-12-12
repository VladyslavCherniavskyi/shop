package com.cherniavskyi.shop.dto.request.product.update;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Set;

public record ProductDtoUpdateRequest(

        @NotBlank(message = "Name cannot be empty")
        String name,

        String description,

        @DecimalMin(value = "0.0", message = "Price should be greater than or equal to 0")
        BigDecimal price,

        @NotNull(message = "StockQuantity cannot be null")
        @Positive(message = "StockQuantity should be greater than 0")
        Integer stockQuantity,

        @NotEmpty(message = "CategoryIds cannot be empty")
        Set<Integer> categoryIds,

        @NotEmpty(message = "SizeIds cannot be empty")
        Set<Long> sizeIds,

        @NotEmpty(message = "BrandIds cannot be empty")
        Set<Long> brandIds,

        @NotEmpty(message = "ColorIds cannot be empty")
        Set<Long> colorIds,

        @NotEmpty(message = "GenderIds cannot be empty")
        Set<Integer> genderIds

) {
}
