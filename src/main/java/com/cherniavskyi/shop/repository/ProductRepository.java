package com.cherniavskyi.shop.repository;

import com.cherniavskyi.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}