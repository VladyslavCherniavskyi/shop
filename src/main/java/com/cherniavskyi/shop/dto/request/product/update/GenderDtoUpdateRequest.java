package com.cherniavskyi.shop.dto.request.product.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenderDtoUpdateRequest(
        @NotBlank(message = "Gender cannot be empty")
        @Size(max = 10, message = "Length limit exceeded. Maximum allowed order details is {max}")
        String htmlCode,
        String gender
) {
}
