package com.cherniavskyi.shop.service.product.photo;

import com.cherniavskyi.shop.dto.file.PhotoDtoRelation;
import com.cherniavskyi.shop.entity.product.photo.Photo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

public interface PhotoService {

    Photo create(PhotoDtoRelation photoDtoRelation, MultipartFile file);

    Photo read(UUID id);

    Resource download(UUID id);

    String delete(UUID id);

}
