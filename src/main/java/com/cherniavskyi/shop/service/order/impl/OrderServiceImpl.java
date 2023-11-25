package com.cherniavskyi.shop.service.order.impl;

import com.cherniavskyi.shop.entity.order.Order;
import com.cherniavskyi.shop.repository.order.OrderRepository;
import com.cherniavskyi.shop.service.order.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order read(Long id) {
        return orderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Order with id:%s is not found", id)
                )
        );
    }

    @Override
    public Order update(Order order) {
        read(order.getId());
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        var order = read(id);
        orderRepository.delete(order);
    }

    @Override
    public Page<Order> getAllByCustomerId(Long id, Pageable pageable) {
        return orderRepository.findAllByCustomerId(id, pageable);
    }

    @Override
    public Order patch(Long id, Order order) {
        read(id);
        return create(order);
    }
}
