package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Brand;
import com.cherniavskyi.shop.repository.product.BrandRepository;
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
class BrandServiceImplTest {

    @Mock
    private BrandRepository brandRepository;
    @Mock
    private Brand brand;
    @InjectMocks
    private BrandServiceImpl brandService;

    @Test
    void create() {
        //given
        var newBrand = new Brand();

        Mockito.doReturn(brand)
                .when(brandRepository)
                .save(newBrand);

        //when
        var actual = brandService.create(newBrand);

        //then
        Assertions.assertEquals(brand, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var id = 1L;

        Mockito.doReturn(Optional.of(brand))
                .when(brandRepository)
                .findById(id);

        //when
        var actual = brandService.read(id);

        //then
        Assertions.assertEquals(brand, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> brandService.read(null)
        );
    }

    @Test
    void update() {
        //given
        var newBrand = new Brand();
        newBrand.setName("name");

        Mockito.doReturn(Optional.of(brand))
                .when(brandRepository)
                .findById(newBrand.getId());

        Mockito.doReturn(brand)
                .when(brandRepository)
                .save(newBrand);

        brand.setName("name");

        //when
        var actual = brandService.update(newBrand);

        //then
        Assertions.assertEquals(brand, actual);
    }

    @Test
    void delete() {
        //giver
        var id = 1L;

        Mockito.doReturn(Optional.of(brand))
                .when(brandRepository)
                .findById(id);

        Mockito.doNothing()
                .when(brandRepository)
                .delete(brand);

        //when
        brandService.delete(id);

        //then
        Mockito.verify(brandRepository).delete(brand);
    }

    @Test
    void getAll() {
        //given
        var count = 10;
        var pageable = Pageable.ofSize(10);
        var brands = Stream.generate(Brand::new)
                .limit(count)
                .collect(Collectors.toList());
        var expected = new PageImpl<>(brands, pageable, brands.size());

        Mockito.doReturn(expected)
                .when(brandRepository)
                .findAll(pageable);

        //when
        var actual = brandService.getAll(pageable);

        //then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(brandRepository).findAll(pageable);
    }
}