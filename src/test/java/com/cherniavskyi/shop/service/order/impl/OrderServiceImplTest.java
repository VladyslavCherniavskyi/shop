package com.cherniavskyi.shop.service.order.impl;

import com.cherniavskyi.shop.entity.order.Order;
import com.cherniavskyi.shop.entity.order.OrderStatus;
import com.cherniavskyi.shop.entity.user.Customer;
import com.cherniavskyi.shop.repository.order.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private Order order;
    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void create() {
        //given
        var newOrder = new Order();

        Mockito.doReturn(order)
                .when(orderRepository)
                .save(newOrder);

        //when
        var actual = orderService.create(newOrder);

        //then
        Assertions.assertEquals(order, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var id = 1L;

        Mockito.doReturn(Optional.of(order))
                .when(orderRepository)
                .findById(id);

        //when
        var actual = orderService.read(id);

        //then
        Assertions.assertEquals(order, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> orderService.read(null)
        );
    }

    @Test
    void update() {
        //given
        var newOrder = new Order();
        newOrder.setOrderStatus(OrderStatus.CREATED);
        newOrder.setCustomer(new Customer());


        Mockito.doReturn(Optional.of(order))
                .when(orderRepository)
                .findById(newOrder.getId());

        Mockito.doReturn(order)
                .when(orderRepository)
                .save(newOrder);

        order.setOrderStatus(OrderStatus.CREATED);

        //when
        var actual = orderService.update(newOrder);

        //then
        Assertions.assertEquals(order, actual);
    }

    @Test
    void delete() {
        //giver
        var id = 1L;

        Mockito.doReturn(Optional.of(order))
                .when(orderRepository)
                .findById(id);

        Mockito.doNothing()
                .when(orderRepository)
                .delete(order);

        //when
        orderService.delete(id);

        //then
        Mockito.verify(orderRepository).delete(order);
    }

    @Test
    void getAllByCustomerId() {
        //given
        var customerId = 1L;
        var count = 10;
        var pageable = Pageable.ofSize(10);
        var orders = Stream.generate(Order::new)
                .limit(count)
                .collect(Collectors.toList());
        var expected = new PageImpl<>(orders, pageable, orders.size());

        Mockito.doReturn(expected)
                .when(orderRepository)
                .findAllByCustomerId(customerId, pageable);

        //when
        var actual = orderService.getAllByCustomerId(customerId, pageable);

        //then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(orderRepository).findAllByCustomerId(customerId, pageable);
    }

    @Test
    void patch() {
        //giver
        var id = 1L;
        var newOrder = new Order();
        newOrder.setOrderStatus(OrderStatus.CREATED);

        Mockito.doReturn(Optional.of(order))
                .when(orderRepository)
                .findById(id);

        Mockito.doReturn(newOrder)
                .when(orderRepository)
                .save(newOrder);

        order.setOrderStatus(OrderStatus.CREATED);

        //when
        var actual = orderService.patch(id, newOrder);

        //then
        Assertions.assertEquals(newOrder, actual);
        Mockito.verify(orderRepository).save(newOrder);
        Mockito.verify(orderRepository).findById(id);
    }
}