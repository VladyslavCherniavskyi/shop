package com.cherniavskyi.shop.repository.user;

import com.cherniavskyi.shop.entity.user.customer.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}