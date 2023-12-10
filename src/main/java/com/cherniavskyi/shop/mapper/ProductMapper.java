package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.query.FilterDtoQuery;
import com.cherniavskyi.shop.dto.query.ProductDtoQuery;
import com.cherniavskyi.shop.dto.request.product.create.*;
import com.cherniavskyi.shop.dto.response.product.*;
import com.cherniavskyi.shop.dto.response.product.photo.PhotoDtoResponse;
import com.cherniavskyi.shop.dto.search.ProductDtoFilterRequest;
import com.cherniavskyi.shop.dto.search.ProductDtoSearchRequest;
import com.cherniavskyi.shop.entity.product.*;
import com.cherniavskyi.shop.entity.product.photo.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.Set;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface ProductMapper {

    ProductDtoResponse mapTo(Product product);

    CategoryDtoResponse mapTo(Category category);

    BrandDtoResponse mapTo(Brand brand);

    ColorDtoResponse mapTo(Color color);

    GenderDtoResponse mapTo(Gender gender);

    SizeDtoResponse mapTo(Size size);

    @Mapping(target = "name", source = "infix")
    ProductDtoQuery mapTo(ProductDtoSearchRequest productDtoSearchRequest);

    FilterDtoQuery mapTo(ProductDtoFilterRequest productDtoFilterRequest);

    Product mapFrom(ProductDtoCreateRequest productDtoCreateRequest);

    Set<Category> mapCategoryFrom(Set<CategoryDtoResponse> categories);

    Set<Size> mapSizeFrom(Set<SizeDtoResponse> sizes);

    Set<Brand> mapBrandFrom(Set<BrandDtoResponse> brands);

    Set<Color> mapColorFrom(Set<ColorDtoResponse> colors);

    Set<Gender> mapGenderFrom(Set<GenderDtoResponse> genders);
    Set<Photo> mapPhotoFrom(Set<PhotoDtoResponse> photos);

    Brand mapFrom(BrandDtoCreateRequest brandDtoCreateRequest);

    Category mapFrom(CategoryDtoCreateRequest categoryDtoCreateRequest);

    Color mapFrom(ColorDtoCreateRequest colorDtoCreateRequest);

    Gender mapTo(GenderDtoCreateRequest genderDtoCreateRequest);

    Size mapFrom(SizeDtoCreateRequest sizeDtoCreateRequest);

}
