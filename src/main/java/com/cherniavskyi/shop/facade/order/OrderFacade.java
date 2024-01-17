package com.cherniavskyi.shop.facade.order;

import com.cherniavskyi.shop.dto.request.order.OrderDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.order.OrderDtoPatchRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDtoResponse;
import com.cherniavskyi.shop.entity.order.OrderStatus;
import com.cherniavskyi.shop.mapper.OrderMapper;
import com.cherniavskyi.shop.service.order.OrderDetailService;
import com.cherniavskyi.shop.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class OrderFacade {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final OrderMapper orderMapper;

    public OrderDtoResponse read(Long id) {
        var order = orderService.read(id);
        return orderMapper.mapTo(order);
    }

    public OrderDtoResponse create(OrderDtoCreateRequest orderDtoCreateRequest) {
        var order = orderMapper.mapFrom(orderDtoCreateRequest);
        order.setOrderStatus(OrderStatus.CREATED);
        var createdOrder = orderService.create(order);

        var orderDetails = orderDtoCreateRequest.orderDetailDtoCreateRequest().stream()
                .map(orderMapper::mapFrom)
                .peek(o -> o.setOrderId(createdOrder))
                .collect(Collectors.toSet());

        orderDetailService.createOrderDetails(orderDetails);
        return orderMapper.mapTo(createdOrder);
    }

    public OrderDtoResponse patch(Long id, OrderDtoPatchRequest orderDtoPatchRequest) {
        var order = orderMapper.mapFrom(orderDtoPatchRequest);
        var patchedOrder = orderService.patch(id, order);
        return orderMapper.mapTo(patchedOrder);
    }
}
