package com.cherniavskyi.shop.repository.product;

import com.cherniavskyi.shop.dto.query.FilterDtoQuery;
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

    @Query("""
            SELECT DISTINCT p FROM Product p
            LEFT JOIN p.genders gender
            LEFT JOIN p.categories category
            LEFT JOIN p.brands brand
            LEFT JOIN p.colors color
            LEFT JOIN p.sizes size
            WHERE
            (:#{#filter.genderId} IS NULL OR gender.id = :#{#filter.genderId})
            AND (:#{#filter.categoryId} IS NULL OR category.id = :#{#filter.categoryId})
            AND (:#{#filter.brandId} IS NULL OR brand.id = :#{#filter.brandId})
            AND (:#{#filter.colorId} IS NULL OR color.id = :#{#filter.colorId})
            AND (:#{#filter.sizeId} IS NULL OR size.id = :#{#filter.sizeId})
            """)
    Page<Product> findAllByFilterDtoQuery(@Param("filter") FilterDtoQuery filterDtoQuery, Pageable pageable);
}