package com.cherniavskyi.shop.dto.request.product.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GenderDtoCreateRequest(
        @NotBlank(message = "Gender cannot be empty")
        @Size(max = 50, message = "Maximum length is {max}")
        String gender
) {
}
