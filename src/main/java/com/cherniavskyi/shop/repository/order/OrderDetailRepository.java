package com.cherniavskyi.shop.repository.order;

import com.cherniavskyi.shop.entity.order.OrderDetail;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {
}