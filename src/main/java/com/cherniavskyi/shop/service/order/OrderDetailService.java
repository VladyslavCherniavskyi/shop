package com.cherniavskyi.shop.service.order;

import com.cherniavskyi.shop.entity.order.OrderDetail;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface OrderDetailService {

    OrderDetail create(OrderDetail product);

    OrderDetail read(OrderDetailKey id);

    OrderDetail update(OrderDetail product);

    void delete(OrderDetailKey id);

    Set<OrderDetail> createOrderDetails(Set<OrderDetail> orderDetails);

    Page<OrderDetail> getAllByOrderId(Long orderId, Pageable pageable);

    OrderDetail patch(OrderDetailKey id, OrderDetail orderDetail);

    void deleteAllByOrderId(Long id);
}
