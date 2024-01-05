package com.cherniavskyi.shop.facade.user;

import com.cherniavskyi.shop.dto.file.ResourceDtoDownload;
import com.cherniavskyi.shop.dto.response.user.UserPhotoDtoResponse;
import com.cherniavskyi.shop.mapper.FileMapper;
import com.cherniavskyi.shop.service.user.UserPhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Component
@Transactional
@RequiredArgsConstructor
public class UserPhotoFacade {

    private final UserPhotoService userPhotoService;
    private final FileMapper fileMapper;

    public UserPhotoDtoResponse create(Long userId, MultipartFile file) {
        var photoDtoRelation = fileMapper.mapTo(userId);
        var userPhoto = userPhotoService.create(photoDtoRelation, file);
        return fileMapper.mapTo(userPhoto);
    }

    public ResourceDtoDownload download(UUID id) {
        var userPhoto = userPhotoService.read(id);
        var resource = userPhotoService.download(id);
        return new ResourceDtoDownload(userPhoto.getName(), resource);
    }
}
