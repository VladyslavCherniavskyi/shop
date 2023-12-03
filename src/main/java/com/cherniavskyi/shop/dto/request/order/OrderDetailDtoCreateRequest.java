package com.cherniavskyi.shop.dto.request.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderDetailDtoCreateRequest(
        @NotNull(message = "ProductId cannot be null") Long productId,
        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity should be greater than 0")
        Integer quantity,
        @NotNull(message = "UnitPrice cannot be null")
        @Positive(message = "UnitPrice should be greater than 0")
        BigDecimal unitPrice
) {
}
