package com.cherniavskyi.shop.service.order;

import com.cherniavskyi.shop.entity.order.OrderDetail;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;

import java.util.Set;

public interface OrderDetailService {

    OrderDetail create(OrderDetail product);

    OrderDetail read(OrderDetailKey id);

    OrderDetail update(OrderDetail product);

    void delete(OrderDetailKey id);

    Set<OrderDetail> createOrderDetails(Set<OrderDetail> orderDetails);
}
