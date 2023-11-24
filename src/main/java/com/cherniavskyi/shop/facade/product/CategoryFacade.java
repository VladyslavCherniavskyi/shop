package com.cherniavskyi.shop.facade.product;

import com.cherniavskyi.shop.dto.response.product.CategoryDtoResponse;
import com.cherniavskyi.shop.mapper.ProductMapper;
import com.cherniavskyi.shop.service.product.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class CategoryFacade {

    private final CategoryService categoryService;
    private final ProductMapper productMapper;

    public Page<CategoryDtoResponse> getAll(Pageable pageable) {
        return categoryService.getAll(pageable)
                .map(productMapper::mapTo);
    }

    public CategoryDtoResponse read(Integer id) {
        var category = categoryService.read(id);
        return productMapper.mapTo(category);
    }

    public CategoryDtoResponse read(String name) {
        var category = categoryService.readByName(name);
        return productMapper.mapTo(category);
    }
}
