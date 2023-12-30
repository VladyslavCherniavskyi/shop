package com.cherniavskyi.shop.repository.user;

import com.cherniavskyi.shop.entity.user.customer.CustomerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerDetail, Long> {
}