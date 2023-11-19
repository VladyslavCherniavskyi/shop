package com.cherniavskyi.shop.service.order;

import com.cherniavskyi.shop.entity.order.Order;

public interface OrderService {

    Order create(Order product);

    Order read(Long id);

    Order update(Order product);

    void delete(Long id);
}
