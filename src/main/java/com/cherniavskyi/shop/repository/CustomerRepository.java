package com.cherniavskyi.shop.repository;

import com.cherniavskyi.shop.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}