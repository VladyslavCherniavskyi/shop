package com.cherniavskyi.shop.facade.product.photo;

import com.cherniavskyi.shop.dto.file.ResourceDtoDownload;
import com.cherniavskyi.shop.dto.response.product.photo.PhotoDtoResponse;
import com.cherniavskyi.shop.mapper.FileMapper;
import com.cherniavskyi.shop.service.product.photo.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Transactional
@RequiredArgsConstructor
public class PhotoFacade {

    private final PhotoService photoService;
    private final FileMapper fileMapper;

    public PhotoDtoResponse create(Long productId, MultipartFile file) {
        var photoDtoRelation = fileMapper.mapTo(productId);
        var photo = photoService.create(photoDtoRelation, file);
        return fileMapper.mapTo(photo);
    }

    public Set<PhotoDtoResponse> createPhotos(Long productId, MultipartFile[] files) {
        return Arrays.stream(files)
                .map(multipartFile -> create(productId, multipartFile))
                .collect(Collectors.toSet());
    }

    public ResourceDtoDownload download(UUID id) {
        var photo = photoService.read(id);
        var resource = photoService.download(id);
        return new ResourceDtoDownload(photo.getName(), resource);
    }
}
