package com.cherniavskyi.shop.dto.request.order;

import jakarta.validation.constraints.NotNull;

import java.util.Set;

public record OrderDtoCreateRequest(
        @NotNull(message = "CustomerId cannot be null") Long customerId,
        @NotNull(message = "OrderDetail cannot be null") Set<OrderDetailDtoCreateRequest> orderDetailDtoCreateRequest
) {
}
