package com.cherniavskyi.shop.dto.request.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record OrderDetailDtoPatchRequest(

        @NotNull(message = "Quantity cannot be null")
        @Positive(message = "Quantity should be greater than 0")
        Integer quantity

) {
}
