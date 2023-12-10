package com.cherniavskyi.shop.dto.request.product.update;

import jakarta.validation.constraints.NotBlank;

public record CategoryDtoUpdateRequest(
        @NotBlank(message = "Name cannot be empty") String name
) {
}
