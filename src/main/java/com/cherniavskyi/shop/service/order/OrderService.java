package com.cherniavskyi.shop.service.order;

import com.cherniavskyi.shop.entity.order.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Order create(Order product);

    Order read(Long id);

    Order update(Order product);

    void delete(Long id);

    Page<Order> getAllByCustomerId(Long id, Pageable pageable);

    Order patch(Long id, Order order);
}
