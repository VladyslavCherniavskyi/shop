package com.cherniavskyi.shop.service;

import com.cherniavskyi.shop.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product create(Product product);

    Product read(Long id);

    Product update(Product product);

    void delete(Long id);

    Page<Product> getAll(Pageable pageable);
}
