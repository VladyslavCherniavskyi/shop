package com.cherniavskyi.shop.service.order.impl;

import com.cherniavskyi.shop.entity.order.Order;
import com.cherniavskyi.shop.entity.order.OrderDetail;
import com.cherniavskyi.shop.entity.order.OrderDetailKey;
import com.cherniavskyi.shop.entity.product.Product;
import com.cherniavskyi.shop.repository.order.OrderDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class OrderDetailServiceImplTest {

    @Mock
    private OrderDetailRepository orderDetailRepository;
    @Mock
    private OrderDetail orderDetail;
    @InjectMocks
    private OrderDetailServiceImpl orderDetailService;

    @Test
    void create() {
        //given
        var newOrderDetail = new OrderDetail();

        Mockito.doReturn(orderDetail)
                .when(orderDetailRepository)
                .save(newOrderDetail);

        //when
        var actual = orderDetailService.create(newOrderDetail);

        //then
        Assertions.assertEquals(orderDetail, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var orderDetailKey = new OrderDetailKey();

        Mockito.doReturn(Optional.of(orderDetail))
                .when(orderDetailRepository)
                .findById(orderDetailKey);

        //when
        var actual = orderDetailService.read(orderDetailKey);

        //then
        Assertions.assertEquals(orderDetail, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> orderDetailService.read(null)
        );
    }

    @Test
    void update() {
        //given
        var newOrderDetail = new OrderDetail();
        newOrderDetail.setQuantity(1);
        newOrderDetail.setOrderId(new Order());
        newOrderDetail.setProductId(new Product());

        Mockito.doReturn(Optional.of(orderDetail))
                .when(orderDetailRepository)
                .findById(newOrderDetail.getOrderDetailKey());

        Mockito.doReturn(orderDetail)
                .when(orderDetailRepository)
                .save(newOrderDetail);

        orderDetail.setQuantity(1);

        //when
        var actual = orderDetailService.update(newOrderDetail);

        //then
        Assertions.assertEquals(orderDetail, actual);
    }

    @Test
    void delete() {
        //giver
        var id = new OrderDetailKey();

        Mockito.doReturn(Optional.of(orderDetail))
                .when(orderDetailRepository)
                .findById(id);

        Mockito.doNothing()
                .when(orderDetailRepository)
                .delete(orderDetail);

        //when
        orderDetailService.delete(id);

        //then
        Mockito.verify(orderDetailRepository).delete(orderDetail);
    }

    @Test
    void createOrderDetails() {
        //given
        var firstOrderDetail = new OrderDetail();
        var secondOrderDetail = new OrderDetail();
        var expectedOrderDetails = List.of(firstOrderDetail, secondOrderDetail, orderDetail);
        var orderDetails = new HashSet<>(Set.of(firstOrderDetail, secondOrderDetail, orderDetail));

        Mockito.doReturn(expectedOrderDetails)
                .when(orderDetailRepository)
                .saveAll(orderDetails);

        //when
        var actual = orderDetailService.createOrderDetails(orderDetails);

        //then
        Assertions.assertEquals(new HashSet<>(expectedOrderDetails), actual);
        Assertions.assertEquals(3, actual.size());
    }

    @Test
    void getAllByOrderId() {
        //given
        var orderId = 1L;
        var count = 10;
        var pageable = Pageable.ofSize(10);
        var orderDetails = Stream.generate(OrderDetail::new)
                .limit(count)
                .collect(Collectors.toList());
        var expected = new PageImpl<>(orderDetails, pageable, orderDetails.size());

        Mockito.doReturn(expected)
                .when(orderDetailRepository)
                .findAllByOrderId(orderId, pageable);

        //when
        var actual = orderDetailService.getAllByOrderId(orderId, pageable);

        //then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(orderDetailRepository).findAllByOrderId(orderId, pageable);
    }

    @Test
    void patch() {
        //giver
        var id = new OrderDetailKey();
        var newOrderDetail = new OrderDetail();
        newOrderDetail.setQuantity(1);

        Mockito.doReturn(Optional.of(orderDetail))
                .when(orderDetailRepository)
                .findById(id);

        Mockito.doReturn(newOrderDetail)
                .when(orderDetailRepository)
                .save(newOrderDetail);

        orderDetail.setQuantity(1);

        //when
        var actual = orderDetailService.patch(id, newOrderDetail);

        //then
        Assertions.assertEquals(newOrderDetail, actual);
        Mockito.verify(orderDetailRepository).save(newOrderDetail);
        Mockito.verify(orderDetailRepository).findById(id);
    }

    @Test
    void deleteAllByOrderId() {
        //giver
        var id = 1L;

        Mockito.doNothing()
                .when(orderDetailRepository)
                .deleteAllByOrderId(id);

        //when
        orderDetailService.deleteAllByOrderId(id);

        //then
        Mockito.verify(orderDetailRepository).deleteAllByOrderId(id);
    }
}