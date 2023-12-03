package com.cherniavskyi.shop.dto.response.order;

import com.cherniavskyi.shop.entity.order.OrderStatus;

public record OrderDtoResponse(
        Long id,
        Long createdDate,
        Long modifiedDate,
        OrderStatus orderStatus,
        Long customerId
) {
}
