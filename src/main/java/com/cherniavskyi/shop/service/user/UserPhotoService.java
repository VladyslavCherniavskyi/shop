package com.cherniavskyi.shop.service.user;

import com.cherniavskyi.shop.dto.file.PhotoDtoRelation;
import com.cherniavskyi.shop.entity.user.UserPhoto;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface UserPhotoService {

    UserPhoto create(PhotoDtoRelation photoDtoRelation, MultipartFile file);

    UserPhoto read(UUID id);

    Resource download(UUID id);

    String delete(UUID id);

}
