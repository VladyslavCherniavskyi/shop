package com.cherniavskyi.shop.facade.product.photo;

import com.cherniavskyi.shop.dto.response.product.photo.CreatePhotoDtoResponse;
import com.cherniavskyi.shop.dto.response.product.photo.DownloadResourceDto;
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

    public CreatePhotoDtoResponse create(MultipartFile file) {
        var photo = photoService.create(file);
        return fileMapper.toDto(photo);
    }

    public Set<CreatePhotoDtoResponse> createPhotos(MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::create)
                .collect(Collectors.toSet());
    }

    public DownloadResourceDto download(UUID id) {
        var photo = photoService.read(id);
        var resource = photoService.download(id);
        return new DownloadResourceDto(photo.getName(), resource);
    }
}
