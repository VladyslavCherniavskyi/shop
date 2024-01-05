package com.cherniavskyi.shop.facade.user;

import com.cherniavskyi.shop.dto.request.user.update.CustomerDetailDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.CustomerDtoResponse;
import com.cherniavskyi.shop.mapper.UserMapper;
import com.cherniavskyi.shop.service.user.CustomerDetailService;
import com.cherniavskyi.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class CustomerDetailFacade {

    private final CustomerDetailService customerDetailService;
    private final UserService userService;
    private final UserMapper userMapper;

    public CustomerDtoResponse read(Long id) {
        var user = userService.read(id);
        var userDtoResponse = userMapper.mapTo(user);
        return userMapper.mapTo(userDtoResponse, user.getCustomerDetail());
    }

    public CustomerDtoResponse update(Long id, CustomerDetailDtoUpdateRequest customerDetailDtoUpdateRequest) {
        var customer = userMapper.mapFrom(customerDetailDtoUpdateRequest);
        customer.setUserId(id);
        var updatedCustomer = customerDetailService.update(customer);
        return userMapper.mapTo(updatedCustomer);
    }
}
