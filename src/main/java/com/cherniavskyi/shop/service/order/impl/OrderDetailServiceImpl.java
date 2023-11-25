package com.cherniavskyi.shop.service.order.impl;

import com.cherniavskyi.shop.entity.order.OrderDetail;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;
import com.cherniavskyi.shop.repository.order.OrderDetailRepository;
import com.cherniavskyi.shop.service.order.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetail create(OrderDetail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail read(OrderDetailKey id) {
        return orderDetailRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("OrderDetail with id:%s is not found", id)
                )
        );
    }

    @Override
    public OrderDetail update(OrderDetail orderDetail) {
        read(orderDetail.getOrderDetailKey());
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public void delete(OrderDetailKey id) {
        var orderDetail = read(id);
        orderDetailRepository.delete(orderDetail);
    }

    @Override
    public Set<OrderDetail> createOrderDetails(@Size(
            max = 20,
            message = "Order details limit exceeded. Maximum allowed order details is {max}")
                                               Set<OrderDetail> orderDetails) {
        return new HashSet<>(orderDetailRepository.saveAll(orderDetails));
    }

    public Page<OrderDetail> getAllByOrderId(Long id, Pageable pageable) {
        return orderDetailRepository.findAllByOrderId(id, pageable);
    }

    @Override
    public OrderDetail patch(OrderDetailKey id, OrderDetail orderDetail) {
        read(id);
        return create(orderDetail);
    }

    @Override
    public void deleteAllByOrderId(Long id) {
        orderDetailRepository.deleteAllByOrderId(id);
    }
}
