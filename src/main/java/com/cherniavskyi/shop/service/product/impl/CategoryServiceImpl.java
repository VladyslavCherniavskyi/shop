package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Category;
import com.cherniavskyi.shop.repository.product.CategoryRepository;
import com.cherniavskyi.shop.service.product.CategoryService;
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

    @Override
    public Category readByName(String name) {
        return Optional.ofNullable(categoryRepository.findByName(name)).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Category with name:%s is not found", name)
                )
        );
    }
}
