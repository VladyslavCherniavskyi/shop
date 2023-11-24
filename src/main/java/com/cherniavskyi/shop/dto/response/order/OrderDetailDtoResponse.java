package com.cherniavskyi.shop.dto.response.order;

import java.math.BigDecimal;

public record OrderDetailDtoResponse(
        Long orderId,
        Long productId,
        Integer quantity,
        BigDecimal unitPrice
) {
}
