package com.cherniavskyi.shop.repository.order;

import com.cherniavskyi.shop.entity.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}