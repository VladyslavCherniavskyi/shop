package com.cherniavskyi.shop.service.impl;

import com.cherniavskyi.shop.entity.Product;
import com.cherniavskyi.shop.repository.ProductRepository;
import com.cherniavskyi.shop.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
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
}
