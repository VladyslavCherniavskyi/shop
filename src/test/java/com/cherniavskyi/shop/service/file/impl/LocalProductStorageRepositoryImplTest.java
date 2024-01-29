package com.cherniavskyi.shop.service.file.impl;

import com.cherniavskyi.shop.repository.file.impl.local.LocalProductStorageRepositoryImpl;
import com.cherniavskyi.shop.util.PathUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;

@SpringBootTest
class LocalProductStorageRepositoryImplTest {

    @InjectMocks
    private LocalProductStorageRepositoryImpl fileStorageService;

    @Test
    void upload() {
        //given
        var file = Mockito.mock(MultipartFile.class);
        var expectedFile = new File("mockedPath/testFile.txt");

        try (var mockedStatic = Mockito.mockStatic(PathUtils.class)) {
            mockedStatic.when(
                            () -> PathUtils.getAbsolutePath(Mockito.anyString(), Mockito.anyString())
                    )
                    .thenReturn(expectedFile.toPath());

            // when
            var actual = fileStorageService.upload(file);

            // then
            Assertions.assertEquals(expectedFile, actual);
        }
    }

    @Test
    void upload_throwsRuntimeException() {
        //given
        try (var mockedStatic = Mockito.mockStatic(PathUtils.class)) {
            mockedStatic.when(
                            () -> PathUtils.getAbsolutePath(Mockito.anyString(), Mockito.anyString())
                    )
                    .thenReturn(null);

            //when
            Assertions.assertThrows(RuntimeException.class,
                    () -> fileStorageService.upload(null)
            );
        }
    }

    @Test
    void download() {
        //given
        var name = "example.txt";
        var filePath = Mockito.mock(Path.class);
        var uri = Mockito.mock(URI.class);

        try (var mockedStatic = Mockito.mockStatic(PathUtils.class)) {
            mockedStatic.when(
                            () -> PathUtils.getAbsolutePath(Mockito.anyString(), Mockito.anyString())
                    )
                    .thenReturn(filePath);

            Mockito.doReturn(uri)
                    .when(filePath)
                    .toUri();

            // when
            var actual = fileStorageService.download(name);

            // then
            Assertions.assertEquals(UrlResource.class, actual.getClass());
        }
    }

    @Test
    void download_throwsRuntimeException() {
        //given
        try (var mockedStatic = Mockito.mockStatic(PathUtils.class)) {
            mockedStatic.when(
                            () -> PathUtils.getAbsolutePath(Mockito.anyString(), Mockito.anyString())
                    )
                    .thenReturn(null);

            //when
            Assertions.assertThrows(RuntimeException.class,
                    () -> fileStorageService.download(null)
            );
        }
    }

    @Test
    void delete() {
        //given
        var name = "example.txt";
        var filePath = Mockito.mock(Path.class);
        var expectedResult = String.format("File named: %s deleted successfully", name);

        try (
                var pathUtilsMockedStatic = Mockito.mockStatic(PathUtils.class);
                var filesMockedStatic = Mockito.mockStatic(Files.class)
        ) {
            pathUtilsMockedStatic.when(
                            () -> PathUtils.getAbsolutePath(Mockito.anyString(), Mockito.anyString())
                    )
                    .thenReturn(filePath);

            // when
            var actual = fileStorageService.delete(name);

            // then
            Assertions.assertEquals(expectedResult, actual);
            filesMockedStatic.verify(() -> Files.delete(filePath));
        }
    }

    @Test
    void delete_throwsRuntimeException() {
        //given
        try (var mockedStatic = Mockito.mockStatic(PathUtils.class)) {
            mockedStatic.when(
                            () -> PathUtils.getAbsolutePath(Mockito.anyString(), Mockito.anyString())
                    )
                    .thenReturn(null);

            //when
            Assertions.assertThrows(RuntimeException.class,
                    () -> fileStorageService.delete(null)
            );
        }
    }
}