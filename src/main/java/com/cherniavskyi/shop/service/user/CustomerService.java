package com.cherniavskyi.shop.service.user;

import com.cherniavskyi.shop.entity.user.Customer;

public interface CustomerService {

    Customer create(Customer product);

    Customer read(Long id);

    Customer update(Customer product);

    void delete(Long id);
}
