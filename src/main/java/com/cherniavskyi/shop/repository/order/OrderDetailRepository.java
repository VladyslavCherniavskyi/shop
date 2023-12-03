package com.cherniavskyi.shop.repository.order;

import com.cherniavskyi.shop.entity.order.OrderDetail;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailKey> {

    @Query("SELECT od FROM OrderDetail od WHERE od.orderId.id = :orderId")
    Page<OrderDetail> findAllByOrderId(@Param("orderId") Long id, Pageable pageable);

    @Modifying
    @Query("SELECT od FROM OrderDetail od WHERE od.orderId.id = :orderId")
    void deleteAllByOrderId(@Param("orderId") Long id);

}