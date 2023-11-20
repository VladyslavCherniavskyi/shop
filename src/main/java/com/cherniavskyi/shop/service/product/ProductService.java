package com.cherniavskyi.shop.service.product;

import com.cherniavskyi.shop.dto.query.ProductDtoQuery;
import com.cherniavskyi.shop.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product create(Product product);

    Product read(Long id);

    Product update(Product product);

    void delete(Long id);

    Page<Product> getAll(Pageable pageable);

    Page<Product> findByDtoQuery(ProductDtoQuery productDtoQuery, Pageable pageable);
}
