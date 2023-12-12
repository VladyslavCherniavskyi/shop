package com.cherniavskyi.shop.dto.request.product.create;

import jakarta.validation.constraints.NotBlank;

public record CategoryDtoCreateRequest(
        @NotBlank(message = "Name cannot be empty") String name
) {
}
