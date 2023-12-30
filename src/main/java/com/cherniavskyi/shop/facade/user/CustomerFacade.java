package com.cherniavskyi.shop.facade.user;

import com.cherniavskyi.shop.dto.request.user.CustomerDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.user.CustomerDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.CustomerDtoResponse;
import com.cherniavskyi.shop.mapper.UserMapper;
import com.cherniavskyi.shop.service.user.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class CustomerFacade {

    private final CustomerService customerService;
    private final UserMapper userMapper;

    public CustomerDtoResponse read(Long id) {
        var customer = customerService.read(id);
        return userMapper.mapTo(customer);
    }

    public CustomerDtoResponse create(CustomerDtoCreateRequest customerDtoCreateRequest) {
        var customer = userMapper.mapFrom(customerDtoCreateRequest);
        var createdCustomer = customerService.create(customer);
        return userMapper.mapTo(createdCustomer);
    }

    public CustomerDtoResponse update(Long id, CustomerDtoUpdateRequest customerDtoUpdateRequest) {
        var customer = userMapper.mapFrom(customerDtoUpdateRequest);
        customer.setUserId(id);
        var updatedCustomer = customerService.update(customer);
        return userMapper.mapTo(updatedCustomer);
    }
}
