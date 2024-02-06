package com.cherniavskyi.shop.service.product.photo.impl;

import com.cherniavskyi.shop.dto.file.FileRequest;
import com.cherniavskyi.shop.dto.file.PhotoDtoRelation;
import com.cherniavskyi.shop.entity.product.photo.Photo;
import com.cherniavskyi.shop.repository.file.ProductFileStorageRepository;
import com.cherniavskyi.shop.repository.product.photo.PhotoRepository;
import com.cherniavskyi.shop.service.product.ProductService;
import com.cherniavskyi.shop.service.product.photo.PhotoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final ProductService productService;
    private final ProductFileStorageRepository fileStorageRepository;

    @Override
    public Photo create(PhotoDtoRelation photoDtoRelation, MultipartFile file) {
        var product = productService.read(photoDtoRelation.productId());
        var request = new FileRequest(file, UUID.randomUUID().toString());
        var photo = Photo.builder()
                .name(file.getOriginalFilename())
                .url("/" + request.name())
                .type(file.getContentType())
                .size(file.getSize())
                .product(product)
                .build();
        var savedPhoto = photoRepository.save(photo);
        fileStorageRepository.upload(request);
        return savedPhoto;
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
        var photo = read(id);
        return Arrays.stream(photo.getUrl().split("/"))
                .reduce((first, second) -> second)
                .map(fileStorageRepository::download)
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("Cannot download photo with id:%s ", id)
                        )
                );
    }

    @Override
    public String delete(UUID id) {
        var photo = read(id);
        var name = Arrays.stream(photo.getUrl().split("/"))
                .reduce((first, second) -> second)
                .orElse("");
        photoRepository.delete(photo);
        return fileStorageRepository.delete(name);
    }
}
