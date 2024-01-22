package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.dto.query.FilterDtoQuery;
import com.cherniavskyi.shop.dto.query.ProductDtoQuery;
import com.cherniavskyi.shop.entity.product.Product;
import com.cherniavskyi.shop.repository.product.ProductRepository;
import com.cherniavskyi.shop.service.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product read(Long id) {
        return productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Product with id:%s is not found", id)
                )
        );
    }

    @Override
    public Product update(Product product) {
        read(product.getId());
        return productRepository.save(product);
    }

    @Override
    public void delete(Long id) {
        var product = read(id);
        productRepository.delete(product);
    }

    @Override
    public Page<Product> getAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> findByDtoQuery(ProductDtoQuery productDtoQuery, Pageable pageable) {
        return productRepository.findByDtoQuery(productDtoQuery, pageable);
    }

    @Override
    public Page<Product> findAllByFilterDtoQuery(FilterDtoQuery filterDtoQuery, Pageable pageable) {
        return productRepository.findAllByFilterDtoQuery(filterDtoQuery, pageable);
    }
}
