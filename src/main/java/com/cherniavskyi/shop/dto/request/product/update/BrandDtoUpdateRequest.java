package com.cherniavskyi.shop.dto.request.product.update;

import jakarta.validation.constraints.NotBlank;

public record BrandDtoUpdateRequest(
        @NotBlank(message = "Name cannot be empty") String name,
        @NotBlank(message = "Country cannot be empty") String country,
        String description
) {
}
