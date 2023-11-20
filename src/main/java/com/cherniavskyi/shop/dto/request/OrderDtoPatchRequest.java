package com.cherniavskyi.shop.dto.request;

import com.cherniavskyi.shop.entity.order.OrderStatus;
import jakarta.validation.constraints.NotNull;

public record OrderDtoPatchRequest(
        @NotNull(message = "OrderStatus cannot be null") OrderStatus orderStatus
) {
}
