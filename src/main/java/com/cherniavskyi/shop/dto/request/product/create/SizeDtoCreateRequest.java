package com.cherniavskyi.shop.dto.request.product.create;

import jakarta.validation.constraints.Size;

public record SizeDtoCreateRequest(
        @Size(max = 3, message = "Maximum length is {max}") String international,
        Integer european
) {
}
