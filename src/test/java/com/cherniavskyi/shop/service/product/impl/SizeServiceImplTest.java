package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Size;
import com.cherniavskyi.shop.repository.product.SizeRepository;
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
class SizeServiceImplTest {

    @Mock
    private SizeRepository sizeRepository;
    @Mock
    private Size size;
    @InjectMocks
    private SizeServiceImpl sizeService;

    @Test
    void create() {
        //given
        var newSize = new Size();

        Mockito.doReturn(size)
                .when(sizeRepository)
                .save(newSize);

        //when
        var actual = sizeService.create(newSize);

        //then
        Assertions.assertEquals(size, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var id = 1;

        Mockito.doReturn(Optional.of(size))
                .when(sizeRepository)
                .findById(id);

        //when
        var actual = sizeService.read(id);

        //then
        Assertions.assertEquals(size, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> sizeService.read(null)
        );
    }

    @Test
    void update() {
        //given
        var newSize = new Size();
        newSize.setEuropean(40);

        Mockito.doReturn(Optional.of(size))
                .when(sizeRepository)
                .findById(newSize.getId());

        Mockito.doReturn(size)
                .when(sizeRepository)
                .save(newSize);

        size.setEuropean(40);

        //when
        var actual = sizeService.update(newSize);

        //then
        Assertions.assertEquals(size, actual);
    }

    @Test
    void delete() {
        //giver
        var id = 1;

        Mockito.doReturn(Optional.of(size))
                .when(sizeRepository)
                .findById(id);

        Mockito.doNothing()
                .when(sizeRepository)
                .delete(size);

        //when
        sizeService.delete(id);

        //then
        Mockito.verify(sizeRepository).delete(size);
    }

    @Test
    void getAll() {
        //given
        var count = 10;
        var pageable = Pageable.ofSize(10);
        var sizes = Stream.generate(Size::new)
                .limit(count)
                .collect(Collectors.toList());
        var expected = new PageImpl<>(sizes, pageable, sizes.size());

        Mockito.doReturn(expected)
                .when(sizeRepository)
                .findAll(pageable);

        //when
        var actual = sizeService.getAll(pageable);

        //then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(sizeRepository).findAll(pageable);
    }
}