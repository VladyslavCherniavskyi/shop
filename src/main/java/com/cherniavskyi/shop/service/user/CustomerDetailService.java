package com.cherniavskyi.shop.service.user;

import com.cherniavskyi.shop.entity.user.customer.CustomerDetail;

public interface CustomerDetailService {

    CustomerDetail create(CustomerDetail customerDetail);

    CustomerDetail read(Long id);

    CustomerDetail update(CustomerDetail customerDetail);

    void delete(Long id);
}
