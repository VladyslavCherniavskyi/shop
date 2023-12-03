package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Gender;
import com.cherniavskyi.shop.repository.product.GenderRepository;
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
class GenderServiceImplTest {

    @Mock
    private GenderRepository genderRepository;
    @Mock
    private Gender gender;
    @InjectMocks
    private GenderServiceImpl genderService;

    @Test
    void create() {
        //given
        var newGender = new Gender();

        Mockito.doReturn(gender)
                .when(genderRepository)
                .save(newGender);

        //when
        var actual = genderService.create(newGender);

        //then
        Assertions.assertEquals(gender, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read() {
        //given
        var id = 1;

        Mockito.doReturn(Optional.of(gender))
                .when(genderRepository)
                .findById(id);

        //when
        var actual = genderService.read(id);

        //then
        Assertions.assertEquals(gender, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> genderService.read(null)
        );
    }

    @Test
    void update() {
        //given
        var newGender = new Gender();
        newGender.setGender("gender");

        Mockito.doReturn(Optional.of(gender))
                .when(genderRepository)
                .findById(newGender.getId());

        Mockito.doReturn(gender)
                .when(genderRepository)
                .save(newGender);

        gender.setGender("gender");

        //when
        var actual = genderService.update(newGender);

        //then
        Assertions.assertEquals(gender, actual);
    }

    @Test
    void delete() {
        //giver
        var id = 1;

        Mockito.doReturn(Optional.of(gender))
                .when(genderRepository)
                .findById(id);

        Mockito.doNothing()
                .when(genderRepository)
                .delete(gender);

        //when
        genderService.delete(id);

        //then
        Mockito.verify(genderRepository).delete(gender);
    }

    @Test
    void getAll() {
        //given
        var count = 10;
        var pageable = Pageable.ofSize(10);
        var genders = Stream.generate(Gender::new)
                .limit(count)
                .collect(Collectors.toList());
        var expected = new PageImpl<>(genders, pageable, genders.size());

        Mockito.doReturn(expected)
                .when(genderRepository)
                .findAll(pageable);

        //when
        var actual = genderService.getAll(pageable);

        //then
        Assertions.assertEquals(expected, actual);
        Mockito.verify(genderRepository).findAll(pageable);
    }
}