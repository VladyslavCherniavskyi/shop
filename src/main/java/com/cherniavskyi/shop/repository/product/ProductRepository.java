package com.cherniavskyi.shop.repository.product;

import com.cherniavskyi.shop.dto.query.ProductDtoQuery;
import com.cherniavskyi.shop.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:#{#productDtoQuery.name}%")
    Page<Product> findByDtoQuery(@Param("productDtoQuery") ProductDtoQuery productDtoQuery, Pageable pageable);


    @Query("SELECT p FROM Product p JOIN p.categories c WHERE c.id = :id")
    Page<Product> findByCategoryId(@Param("id") Integer categoryId, Pageable pageable);
}