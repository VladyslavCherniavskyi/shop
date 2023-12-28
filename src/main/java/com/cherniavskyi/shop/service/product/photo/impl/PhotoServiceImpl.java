package com.cherniavskyi.shop.service.product.photo.impl;

import com.cherniavskyi.shop.dto.file.PhotoDtoRelation;
import com.cherniavskyi.shop.entity.product.photo.Photo;
import com.cherniavskyi.shop.repository.product.photo.PhotoRepository;
import com.cherniavskyi.shop.service.file.FileStorageService;
import com.cherniavskyi.shop.service.product.ProductService;
import com.cherniavskyi.shop.service.product.photo.PhotoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final ProductService productService;
    private final FileStorageService fileStorageService;

    @Override
    public Photo create(PhotoDtoRelation photoDtoRelation, MultipartFile file) {
        var product = productService.read(photoDtoRelation.productId());
        var uploadedFile = fileStorageService.upload(file);
        var photo = Photo.builder()
                .name(file.getOriginalFilename())
                .url(uploadedFile.toString())
                .type(file.getContentType())
                .size(file.getSize())
                .product(product)
                .build();
        return photoRepository.save(photo);
    }

    @Override
    public Photo read(UUID id) {
        return photoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Photo with id:%s is not found", id)
                )
        );
    }

    @Override
    public Resource download(UUID id) {
        return Optional.ofNullable(read(id))
                .flatMap(photo -> Arrays.stream(photo.getUrl().split("/"))
                        .reduce((first, second) -> second))
                .map(fileStorageService::download)
                .orElseThrow();//TODO I should add throw
    }

    @Override
    public void delete(UUID id) {
        Optional.ofNullable(read(id))
                .ifPresent(photo -> Arrays.stream(photo.getUrl().split("/"))
                        .reduce((first, second) -> second)
                        .ifPresent(name -> {
                            fileStorageService.delete(name);
                            photoRepository.delete(photo);
                        })
                );
    }
}
