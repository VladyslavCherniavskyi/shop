package com.cherniavskyi.shop.dto.request.order;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderDetailDtoCreateRequest(
        @NotNull(message = "ProductId cannot be null") Long productId,
        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity should be greater than 0")
        Integer quantity,
        @DecimalMin(value = "0.0", message = "UnitPrice should be greater than or equal to 0")
        BigDecimal unitPrice
) {
}
