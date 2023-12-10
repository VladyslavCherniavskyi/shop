package com.cherniavskyi.shop.dto.request.product.create;

import jakarta.validation.constraints.NotBlank;

public record BrandDtoCreateRequest(
        @NotBlank(message = "Name cannot be empty") String name,
        @NotBlank(message = "Country cannot be empty") String country,
        String description
) {
}
