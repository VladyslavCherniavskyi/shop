package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.dto.file.PhotoDtoRelation;
import com.cherniavskyi.shop.entity.user.UserPhoto;
import com.cherniavskyi.shop.repository.user.UserPhotoRepository;
import com.cherniavskyi.shop.service.file.FileStorageService;
import com.cherniavskyi.shop.service.user.UserPhotoService;
import com.cherniavskyi.shop.service.user.UserService;
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
public class UserPhotoServiceImpl implements UserPhotoService {

    private final UserService userService;
    private final UserPhotoRepository userPhotoRepository;
    private final FileStorageService fileStorageService;

    @Override
    public UserPhoto create(PhotoDtoRelation photoDtoRelation, MultipartFile file) {
        var user = userService.read(photoDtoRelation.userId());
        var uploadedFile = fileStorageService.upload(file);
        var photo = UserPhoto.builder()
                .name(file.getOriginalFilename())
                .url(uploadedFile.toString())
                .type(file.getContentType())
                .size(file.getSize())
                .user(user)
                .build();
        return userPhotoRepository.save(photo);
    }

    @Override
    public UserPhoto read(UUID id) {
        return userPhotoRepository.findById(id).orElseThrow(
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
                .orElseThrow(
                        () -> new EntityNotFoundException(
                                String.format("Cannot download photo with id:%s ", id)
                        )
                );
    }

    @Override
    public void delete(UUID id) {
        Optional.ofNullable(read(id))
                .ifPresent(photo -> Arrays.stream(photo.getUrl().split("/"))
                        .reduce((first, second) -> second)
                        .ifPresent(name -> {
                            fileStorageService.delete(name);
                            userPhotoRepository.delete(photo);
                        })
                );
    }
}
