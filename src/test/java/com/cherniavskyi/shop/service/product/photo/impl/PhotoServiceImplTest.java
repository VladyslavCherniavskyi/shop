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
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
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
    @InjectMocks
    private PhotoServiceImpl photoService;

    @Test
    void create() {
        //given
        var mockedPhoto = Photo.builder()
                .name("testFile.png")
                .url("mockedPath/testFile.png")
                .type("image/png")
                .size(1L)
                .build();

        var mockFile = new MockMultipartFile(
                "testFile",
                "testFile.png",
                "image/png",
                "File content".getBytes()
        );

        Mockito.doReturn(new File("mockedPath/testFile.png"))
                .when(fileStorageService)
                .upload(Mockito.any(MultipartFile.class));

        Mockito.doReturn(mockedPhoto)
                .when(photoRepository)
                .save(Mockito.any(Photo.class));

        //when
        var actual = photoService.create(mockFile);

        //then
        Assertions.assertEquals(mockedPhoto, actual);
        Mockito.verify(fileStorageService).upload(Mockito.any(MultipartFile.class));
        Mockito.verify(photoRepository).save(Mockito.any(Photo.class));
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
    void download() {
        //giver
        var id = Mockito.mock(UUID.class);
        var name = "testFile.png";
        var url = String.format("mockedPath/%s", name);
        var resource = Mockito.mock(Resource.class);

        Mockito.doReturn(Optional.of(photo))
                .when(photoRepository)
                .findById(id);

        Mockito.doReturn(url)
                .when(photo)
                .getUrl();

        Mockito.doReturn(resource)
                .when(fileStorageService)
                .download(name);

        //when
        var actual = photoService.download(id);

        //then
        Assertions.assertEquals(resource, actual);
        Mockito.verify(fileStorageService).download(name);
    }

    @Test
    void delete() {
        //giver
        var id = Mockito.mock(UUID.class);
        var name = "testFile.png";
        var url = String.format("mockedPath/%s", name);
        var successfully = String.format("File named: %s deleted successfully", name);

        Mockito.doReturn(Optional.of(photo))
                .when(photoRepository)
                .findById(id);

        Mockito.doReturn(url)
                .when(photo)
                .getUrl();

        Mockito.doReturn(successfully)
                .when(fileStorageService)
                .delete(name);

        Mockito.doNothing()
                .when(photoRepository)
                .delete(photo);

        //when
        photoService.delete(id);

        //then
        Mockito.verify(photoRepository).delete(photo);
        Mockito.verify(fileStorageService).delete(name);
    }
}