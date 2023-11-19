package com.cherniavskyi.shop.service.impl;

import com.cherniavskyi.shop.entity.Category;
import com.cherniavskyi.shop.repository.CategoryRepository;
import com.cherniavskyi.shop.service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category read(Integer id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Category with id:%s is not found", id)
                )
        );
    }

    @Override
    public Category update(Category category) {
        read(category.getId());
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Integer id) {
        var category = read(id);
        categoryRepository.delete(category);
    }

    @Override
    public Page<Category> getAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }
}
