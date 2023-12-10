package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.query.FilterDtoQuery;
import com.cherniavskyi.shop.dto.query.ProductDtoQuery;
import com.cherniavskyi.shop.dto.request.product.create.*;
import com.cherniavskyi.shop.dto.response.product.*;
import com.cherniavskyi.shop.dto.search.ProductDtoFilterRequest;
import com.cherniavskyi.shop.dto.search.ProductDtoSearchRequest;
import com.cherniavskyi.shop.entity.product.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

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

    @Mapping(target = "categories", source = "categoryIds")
    @Mapping(target = "sizes", source = "sizeIds")
    @Mapping(target = "brands", source = "brandIds")
    @Mapping(target = "colors", source = "colorIds")
    @Mapping(target = "genders", source = "genderIds")
    Product mapFrom(ProductDtoCreateRequest productDtoCreateRequest);

    Brand mapFrom(BrandDtoCreateRequest brandDtoCreateRequest);

    Category mapFrom(CategoryDtoCreateRequest categoryDtoCreateRequest);

    Color mapFrom(ColorDtoCreateRequest colorDtoCreateRequest);

    Gender mapTo(GenderDtoCreateRequest genderDtoCreateRequest);

    Size mapFrom(SizeDtoCreateRequest sizeDtoCreateRequest);

    Category mapToCategory(Integer id);

    Size mapToSize(Long id);

    Brand mapToBrand(Long id);

    Color mapToColor(Long id);

    Gender mapToGender(Integer id);

}
