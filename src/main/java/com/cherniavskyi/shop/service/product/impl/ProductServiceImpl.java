package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Product;
import com.cherniavskyi.shop.repository.product.ProductRepository;
import com.cherniavskyi.shop.service.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
    public Page<Product> getAllByCategoryName(String name, Pageable pageable) {
        return Optional.ofNullable(productRepository.findAllByCategoryName(name, pageable)).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Category with name:%s is not found", name)
                )
        );
    }
}
