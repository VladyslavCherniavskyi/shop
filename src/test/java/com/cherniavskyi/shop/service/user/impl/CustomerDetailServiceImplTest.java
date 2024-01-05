package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.entity.user.customer.CustomerDetail;
import com.cherniavskyi.shop.repository.user.CustomerDetailRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Optional;

@SpringBootTest
class CustomerDetailServiceImplTest {

    @Mock
    private CustomerDetailRepository customerDetailRepository;
    @Mock
    private CustomerDetail customerDetail;
    @InjectMocks
    private CustomerDetailServiceImpl customerService;

    @Test
    void create() {
        //given
        var newCustomer = new CustomerDetail();

        Mockito.doReturn(customerDetail)
                .when(customerDetailRepository)
                .save(newCustomer);

        //when
        var actual = customerService.create(newCustomer);

        //then
        Assertions.assertEquals(customerDetail, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var id = 1L;

        Mockito.doReturn(Optional.of(customerDetail))
                .when(customerDetailRepository)
                .findById(id);

        //when
        var actual = customerService.read(id);

        //then
        Assertions.assertEquals(customerDetail, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> customerService.read(null)
        );
    }

    @Test
    void update() {
        //given
        var createAccountDate = new Date(1);
        var newCustomer = new CustomerDetail();
        newCustomer.setCreateAccount(createAccountDate);

        Mockito.doReturn(Optional.of(customerDetail))
                .when(customerDetailRepository)
                .findById(newCustomer.getUserId());

        Mockito.doReturn(customerDetail)
                .when(customerDetailRepository)
                .save(newCustomer);

        customerDetail.setCreateAccount(createAccountDate);

        //when
        var actual = customerService.update(newCustomer);

        //then
        Assertions.assertEquals(customerDetail, actual);
    }

    @Test
    void delete() {
        //giver
        var id = 1L;

        Mockito.doReturn(Optional.of(customerDetail))
                .when(customerDetailRepository)
                .findById(id);

        Mockito.doNothing()
                .when(customerDetailRepository)
                .delete(customerDetail);

        //when
        customerService.delete(id);

        //then
        Mockito.verify(customerDetailRepository).delete(customerDetail);
    }
}