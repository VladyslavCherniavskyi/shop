package com.cherniavskyi.shop.dto.request;

import com.cherniavskyi.shop.entity.order.OrderStatus;

public record OrderDtoPatchRequest(
        OrderStatus orderStatus
) {
}
