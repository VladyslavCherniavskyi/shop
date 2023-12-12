package com.cherniavskyi.shop.dto.request.order;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record OrderDtoCreateRequest(
        @NotNull(message = "CustomerId cannot be null") Long customerId,
        @NotEmpty(message = "OrderDetails cannot be empty") Set<OrderDetailDtoCreateRequest> orderDetailDtoCreateRequest
) {
}
