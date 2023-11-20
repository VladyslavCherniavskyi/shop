package com.cherniavskyi.shop.facade.order;

import com.cherniavskyi.shop.dto.request.OrderDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.OrderDtoPatchRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDtoResponse;
import com.cherniavskyi.shop.entity.order.OrderStatus;
import com.cherniavskyi.shop.mapper.OrderMapper;
import com.cherniavskyi.shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderDtoResponse read(Long id) {
        var order = orderService.read(id);
        return orderMapper.mapTo(order);
    }

    public Page<OrderDtoResponse> getAllByCustomerId(Long id, Pageable pageable) {
        return orderService.getAllByCustomerId(id, pageable)
                .map(orderMapper::mapTo);
    }

    public OrderDtoResponse create(OrderDtoCreateRequest orderDtoCreateRequest) {
        var order = orderMapper.mapFrom(orderDtoCreateRequest);
        order.setOrderStatus(OrderStatus.CREATED);
        var createdOrder = orderService.create(order);
        return orderMapper.mapTo(createdOrder);
    }

    public OrderDtoResponse patch(Long id, OrderDtoPatchRequest orderDtoPatchRequest) {
        var order = orderMapper.mapFrom(orderDtoPatchRequest);
        var patchedOrder = orderService.patch(id, order);
        return orderMapper.mapTo(patchedOrder);
    }
}
