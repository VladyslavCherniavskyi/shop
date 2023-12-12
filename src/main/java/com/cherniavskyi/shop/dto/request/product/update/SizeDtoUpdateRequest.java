package com.cherniavskyi.shop.dto.request.product.update;

import jakarta.validation.constraints.Size;

public record SizeDtoUpdateRequest(
        @Size(max = 3, message = "Maximum length is {max}") String international,
        Integer european
) {
}
