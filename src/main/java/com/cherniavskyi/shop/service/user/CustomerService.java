package com.cherniavskyi.shop.service.user;

import com.cherniavskyi.shop.entity.user.Customer;

public interface CustomerService {

    Customer create(Customer customer);

    Customer read(Long id);

    Customer update(Customer customer);

    void delete(Long id);
}
