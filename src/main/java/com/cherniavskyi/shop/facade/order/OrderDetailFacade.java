package com.cherniavskyi.shop.facade.order;

import com.cherniavskyi.shop.dto.request.order.OrderDetailDtoPatchRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDetailDtoResponse;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;
import com.cherniavskyi.shop.mapper.OrderMapper;
import com.cherniavskyi.shop.service.order.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class OrderDetailFacade {

    private final OrderDetailService orderDetailService;
    private final OrderMapper orderMapper;

    public OrderDetailDtoResponse patch(OrderDetailKey id, OrderDetailDtoPatchRequest orderDetailDtoPatchRequest) {
        var orderDetail = orderMapper.mapFrom(orderDetailDtoPatchRequest);
        var patchedOrderDetail = orderDetailService.patch(id, orderDetail);
        return orderMapper.mapTo(patchedOrderDetail);
    }
}
