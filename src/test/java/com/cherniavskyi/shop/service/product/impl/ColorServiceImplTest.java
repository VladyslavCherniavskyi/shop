package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Color;
import com.cherniavskyi.shop.repository.product.ColorRepository;
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
class ColorServiceImplTest {

    @Mock
    private ColorRepository colorRepository;
    @Mock
    private Color color;
    @InjectMocks
    private ColorServiceImpl colorService;

    @Test
    void create() {
        //given
        var newColor = new Color();

        Mockito.doReturn(color)
                .when(colorRepository)
                .save(newColor);

        //when
        var actual = colorService.create(newColor);

        //then
        Assertions.assertEquals(color, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var id = 1L;

        Mockito.doReturn(Optional.of(color))
                .when(colorRepository)
                .findById(id);

        //when
        var actual = colorService.read(id);

        //then
        Assertions.assertEquals(color, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> colorService.read(null)
        );
    }

    @Test
    void update() {
        //given
        var newColor = new Color();
        newColor.setName("name");

        Mockito.doReturn(Optional.of(color))
                .when(colorRepository)
                .findById(newColor.getId());

        Mockito.doReturn(color)
                .when(colorRepository)
                .save(newColor);

        color.setName("name");

        //when
        var actual = colorService.update(newColor);

        //then
        Assertions.assertEquals(color, actual);
    }

    @Test
    void delete() {
        //giver
        var id = 1L;

        Mockito.doReturn(Optional.of(color))
                .when(colorRepository)
                .findById(id);

        Mockito.doNothing()
                .when(colorRepository)
                .delete(color);

        //when
        colorService.delete(id);

        //then
        Mockito.verify(colorRepository).delete(color);
    }

    @Test
    void getAll() {
        //given
        var count = 10;
        var pageable = Pageable.ofSize(10);
        var colors = Stream.generate(Color::new)
                .limit(count)
                .collect(Collectors.toList());
        var expected = new PageImpl<>(colors, pageable, colors.size());

        Mockito.doReturn(expected)
                .when(colorRepository)
                .findAll(pageable);

        //when
        var actual = colorService.getAll(pageable);

        //then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(colorRepository).findAll(pageable);
    }
}