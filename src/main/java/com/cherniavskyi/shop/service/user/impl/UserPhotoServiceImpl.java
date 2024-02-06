package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.dto.file.FileRequest;
import com.cherniavskyi.shop.dto.file.PhotoDtoRelation;
import com.cherniavskyi.shop.entity.user.UserPhoto;
import com.cherniavskyi.shop.repository.file.UserFileStorageRepository;
import com.cherniavskyi.shop.repository.user.UserPhotoRepository;
import com.cherniavskyi.shop.service.user.UserPhotoService;
import com.cherniavskyi.shop.service.user.UserService;
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
public class UserPhotoServiceImpl implements UserPhotoService {

    private final UserService userService;
    private final UserPhotoRepository userPhotoRepository;
    private final UserFileStorageRepository fileStorageRepository;

    @Override
    public UserPhoto create(PhotoDtoRelation photoDtoRelation, MultipartFile file) {
        var user = userService.read(photoDtoRelation.userId());
        var request = new FileRequest(file, UUID.randomUUID().toString());
        var photo = UserPhoto.builder()
                .name(file.getOriginalFilename())
                .url("/" + request.name())
                .type(file.getContentType())
                .size(file.getSize())
                .user(user)
                .build();
        var savedPhoto = userPhotoRepository.save(photo);
        fileStorageRepository.upload(request);
        return savedPhoto;
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
        var userPhoto = read(id);
        return Arrays.stream(userPhoto.getUrl().split("/"))
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
        var userPhoto = read(id);
        var name = Arrays.stream(userPhoto.getUrl().split("/"))
                .reduce((first, second) -> second)
                .orElse("");
        userPhotoRepository.deleteById(id);
        return fileStorageRepository.delete(name);
    }
}
