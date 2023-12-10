package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.response.product.photo.PhotoDtoResponse;
import com.cherniavskyi.shop.entity.product.photo.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface FileMapper {

    PhotoDtoResponse mapTo(Photo photo);

}