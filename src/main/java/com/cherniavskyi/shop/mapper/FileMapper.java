package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.file.PhotoDtoRelation;
import com.cherniavskyi.shop.dto.response.product.photo.PhotoDtoResponse;
import com.cherniavskyi.shop.dto.response.user.UserPhotoDtoResponse;
import com.cherniavskyi.shop.entity.product.photo.Photo;
import com.cherniavskyi.shop.entity.user.UserPhoto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FileMapper {

    @Mapping(target = "productId", source = "product.id")
    PhotoDtoResponse mapTo(Photo photo);

    @Mapping(target = "userId", source = "relationId")
    @Mapping(target = "productId", source = "relationId")
    PhotoDtoRelation mapTo(Long relationId);

    @Mapping(target = "userId", source = "user.id")
    UserPhotoDtoResponse mapTo(UserPhoto userPhoto);
}
