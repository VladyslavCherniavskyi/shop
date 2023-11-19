package com.cherniavskyi.shop.service.impl.order;

import com.cherniavskyi.shop.entity.order.Order;
import com.cherniavskyi.shop.repository.order.OrderRepository;
import com.cherniavskyi.shop.service.order.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
}
