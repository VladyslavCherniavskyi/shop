package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.dto.query.FilterDtoQuery;
import com.cherniavskyi.shop.dto.query.ProductDtoQuery;
import com.cherniavskyi.shop.entity.product.Category;
import com.cherniavskyi.shop.entity.product.Product;
import com.cherniavskyi.shop.repository.product.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private Product product;
    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void create() {
        //given
        var newProduct = new Product();

        Mockito.doReturn(product)
                .when(productRepository)
                .save(newProduct);

        //when
        var actual = productService.create(newProduct);

        //then
        Assertions.assertEquals(product, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var id = 1L;

        Mockito.doReturn(Optional.of(product))
                .when(productRepository)
                .findById(id);

        //when
        var actual = productService.read(id);

        //then
        Assertions.assertEquals(product, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> productService.read(null)
        );
    }

    @Test
    void update() {
        //given
        var newProduct = new Product();
        newProduct.setName("name");

        Mockito.doReturn(Optional.of(product))
                .when(productRepository)
                .findById(newProduct.getId());

        Mockito.doReturn(product)
                .when(productRepository)
                .save(newProduct);

        product.setName("name");

        //when
        var actual = productService.update(newProduct);

        //then
        Assertions.assertEquals(product, actual);
    }

    @Test
    void delete() {
        //giver
        var id = 1L;

        Mockito.doReturn(Optional.of(product))
                .when(productRepository)
                .findById(id);

        Mockito.doNothing()
                .when(productRepository)
                .delete(product);

        //when
        productService.delete(id);

        //then
        Mockito.verify(productRepository).delete(product);
    }

    @Test
    void getAll() {
        //given
        var count = 10;
        var pageable = Pageable.ofSize(10);
        var products = Stream.generate(Product::new)
                .limit(count)
                .collect(Collectors.toList());
        var expected = new PageImpl<>(products, pageable, products.size());

        Mockito.doReturn(expected)
                .when(productRepository)
                .findAll(pageable);

        //when
        var actual = productService.getAll(pageable);

        //then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(productRepository).findAll(pageable);
    }

    @Test
    void findByDtoQuery() {
        //given
        var productDtoQuery = new ProductDtoQuery("Number: 1");
        var count = 11;
        var pageable = Pageable.ofSize(11);
        var index = new AtomicInteger(1);

        var products = Stream.generate(Product::new)
                .limit(count)
                .peek(p -> p.setName("productNumber: " + index.getAndIncrement()))
                .toList();

        var filteredProducts = products.stream()
                .filter(p -> p.getName().contains(productDtoQuery.name()))
                .toList();

        var expected = new PageImpl<>(filteredProducts, pageable, filteredProducts.size());

        Mockito.doReturn(expected)
                .when(productRepository)
                .findByDtoQuery(productDtoQuery, pageable);

        //when
        var actual = productService.findByDtoQuery(productDtoQuery, pageable);

        //then
        Assertions.assertEquals(expected, actual);
        Assertions.assertEquals(3, actual.toSet().size());
    }

    @Test
    void findAllByFilterDtoQuery() {
        //given
        var filterDtoQuery = Mockito.mock(FilterDtoQuery.class);
        var pageable = Pageable.ofSize(10);
        var mockProducts = List.of(
                Mockito.mock(Product.class),
                Mockito.mock(Product.class),
                Mockito.mock(Product.class)
        );
        var expected = new PageImpl<>(mockProducts, pageable, mockProducts.size());

        Mockito.doReturn(expected)
                .when(productRepository)
                .findAllByFilterDtoQuery(filterDtoQuery, pageable);

        //when
        var actual = productService.findAllByFilterDtoQuery(filterDtoQuery, pageable);

        //then
        Assertions.assertEquals(expected, actual);
    }
}