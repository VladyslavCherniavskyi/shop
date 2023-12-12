package com.cherniavskyi.shop.dto.request.product.create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ColorDtoCreateRequest(
        @NotBlank(message = "Name cannot be empty") String name,
        @Size(max = 10, message = "Maximum length is {max}") String htmlCode,
        Integer redComponent
) {
}
