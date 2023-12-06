package com.cherniavskyi.shop.service.product.photo.impl;

import com.cherniavskyi.shop.entity.product.photo.Photo;
import com.cherniavskyi.shop.repository.product.photo.PhotoRepository;
import com.cherniavskyi.shop.service.file.impl.FileStorageServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
class PhotoServiceImplTest {

    @Mock
    private PhotoRepository photoRepository;
    @Mock
    private FileStorageServiceImpl fileStorageService;
    @Mock
    private Photo photo;
    @Mock
    private MultipartFile file;
    @InjectMocks
    private PhotoServiceImpl photoService;

    @Test
    void create() {
        //given
        var newPhoto = Mockito.mock(Photo.class);
        var file = Mockito.mock(MultipartFile.class);

        Mockito.doReturn(photo)
                .when(photoRepository)
                .save(newPhoto);

        //when
        var actual = photoService.create(file);

        //then
        Assertions.assertEquals(photo, actual);
        Assertions.assertNotNull(actual);
        Mockito.verify(fileStorageService).upload(file);
    }

    @Test
    void read() {
        //given
        var id = Mockito.mock(UUID.class);

        Mockito.doReturn(Optional.of(photo))
                .when(photoRepository)
                .findById(id);

        //when
        var actual = photoService.read(id);

        //then
        Assertions.assertEquals(photo, actual);
        Assertions.assertNotNull(actual);
    }

    @Test
    void read_throwEntityNotFoundException() {
        //when
        Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> photoService.read(null)
        );
    }

    @Test
    void delete() {
        //giver
        var id = Mockito.mock(UUID.class);

        Mockito.doReturn(Optional.of(photo))
                .when(photoRepository)
                .findById(id);

        Mockito.doNothing()
                .when(photoRepository)
                .delete(photo);

        //when
        photoService.delete(id);

        //then
        Mockito.verify(photoRepository).delete(photo);
    }
}