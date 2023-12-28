package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.entity.user.customer.Customer;
import com.cherniavskyi.shop.repository.user.CustomerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private Customer customer;
    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void create() {
        //given
        var newCustomer = new Customer();

        Mockito.doReturn(customer)
                .when(customerRepository)
                .save(newCustomer);

        //when
        var actual = customerService.create(newCustomer);

        //then
        Assertions.assertEquals(customer, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var id = 1L;

        Mockito.doReturn(Optional.of(customer))
                .when(customerRepository)
                .findById(id);

        //when
        var actual = customerService.read(id);

        //then
        Assertions.assertEquals(customer, actual);
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
        var newCustomer = new Customer();
        newCustomer.setFirstName("firstName");

        Mockito.doReturn(Optional.of(customer))
                .when(customerRepository)
                .findById(newCustomer.getId());

        Mockito.doReturn(customer)
                .when(customerRepository)
                .save(newCustomer);

        customer.setFirstName("firstName");

        //when
        var actual = customerService.update(newCustomer);

        //then
        Assertions.assertEquals(customer, actual);
    }

    @Test
    void delete() {
        //giver
        var id = 1L;

        Mockito.doReturn(Optional.of(customer))
                .when(customerRepository)
                .findById(id);

        Mockito.doNothing()
                .when(customerRepository)
                .delete(customer);

        //when
        customerService.delete(id);

        //then
        Mockito.verify(customerRepository).delete(customer);
    }
}