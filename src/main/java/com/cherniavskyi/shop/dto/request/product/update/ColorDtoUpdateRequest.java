package com.cherniavskyi.shop.dto.request.product.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ColorDtoUpdateRequest(
        @NotBlank(message = "Name cannot be empty") String name,
        @Size(max = 10, message = "Length limit exceeded. Maximum allowed order details is {max}") String htmlCode,
        Integer redComponent
) {
}
