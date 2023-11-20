package com.cherniavskyi.shop.dto.request;

import jakarta.validation.constraints.NotNull;

public record OrderDtoCreateRequest(
        @NotNull(message = "CustomerId cannot be null") Long customerId
) {
}
