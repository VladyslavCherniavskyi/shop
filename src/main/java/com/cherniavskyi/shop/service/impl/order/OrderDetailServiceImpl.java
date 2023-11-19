package com.cherniavskyi.shop.service.impl.order;

import com.cherniavskyi.shop.entity.order.OrderDetail;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;
import com.cherniavskyi.shop.repository.order.OrderDetailRepository;
import com.cherniavskyi.shop.service.order.OrderDetailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
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
}
