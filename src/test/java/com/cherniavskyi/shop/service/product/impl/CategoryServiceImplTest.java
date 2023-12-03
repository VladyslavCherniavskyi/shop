package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Category;
import com.cherniavskyi.shop.repository.product.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class CategoryServiceImplTest {

    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private Category category;
    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Test
    void create() {
        //given
        var newCategory = new Category();

        Mockito.doReturn(category)
                .when(categoryRepository)
                .save(newCategory);

        //when
        var actual = categoryService.create(newCategory);

        //then
        Assertions.assertEquals(category, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var id = 1;

        Mockito.doReturn(Optional.of(category))
                .when(categoryRepository)
                .findById(id);

        //when
        var actual = categoryService.read(id);

        //then
        Assertions.assertEquals(category, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> categoryService.read(null)
        );
    }

    @Test
    void update() {
        //given
        var newCategory = new Category();
        newCategory.setName("name");

        Mockito.doReturn(Optional.of(category))
                .when(categoryRepository)
                .findById(newCategory.getId());

        Mockito.doReturn(category)
                .when(categoryRepository)
                .save(newCategory);

        category.setName("name");

        //when
        var actual = categoryService.update(newCategory);

        //then
        Assertions.assertEquals(category, actual);
    }

    @Test
    void delete() {
        //giver
        var id = 1;

        Mockito.doReturn(Optional.of(category))
                .when(categoryRepository)
                .findById(id);

        Mockito.doNothing()
                .when(categoryRepository)
                .delete(category);

        //when
        categoryService.delete(id);

        //then
        Mockito.verify(categoryRepository).delete(category);
    }

    @Test
    void getAll() {
        //given
        var count = 10;
        var pageable = Pageable.ofSize(10);
        var categories = Stream.generate(Category::new)
                .limit(count)
                .collect(Collectors.toList());
        var expected = new PageImpl<>(categories, pageable, categories.size());

        Mockito.doReturn(expected)
                .when(categoryRepository)
                .findAll(pageable);

        //when
        var actual = categoryService.getAll(pageable);

        //then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(categoryRepository).findAll(pageable);
    }

    @Test
    void readByName() {
        //given
        var name = "name";
        var expected = category;
        expected.setName(name);

        Mockito.doReturn(category)
                .when(categoryRepository)
                .findByName(name);

        //when
        var actual = categoryService.readByName(name);

        //then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void readByName_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> categoryService.readByName(null)
        );
    }
}