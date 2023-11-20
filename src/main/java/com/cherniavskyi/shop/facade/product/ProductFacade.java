package com.cherniavskyi.shop.facade.product;

import com.cherniavskyi.shop.dto.response.product.ProductDtoResponse;
import com.cherniavskyi.shop.mapper.ProductMapper;
import com.cherniavskyi.shop.service.product.CategoryService;
import com.cherniavskyi.shop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public Page<ProductDtoResponse> getAll(Pageable pageable) {
        return productService.getAll(pageable)
                .map(productMapper::mapTo);
    }

    public ProductDtoResponse read(Long id) {
        var product = productService.read(id);
        return productMapper.mapTo(product);
    }

    public Page<ProductDtoResponse> getAllByCategoryName(String name, Pageable pageable) {
        var categoryName = categoryService.readByName(name).getName();
        return productService.getAllByCategoryName(categoryName, pageable)
                .map(productMapper::mapTo);

    }
}
