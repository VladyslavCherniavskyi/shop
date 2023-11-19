package com.cherniavskyi.shop.service;

import com.cherniavskyi.shop.entity.Customer;

public interface CustomerService {

    Customer create(Customer product);

    Customer read(Long id);

    Customer update(Customer product);

    void delete(Long id);
}
