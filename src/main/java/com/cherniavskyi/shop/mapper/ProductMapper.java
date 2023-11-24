package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.response.product.CategoryDtoResponse;
import com.cherniavskyi.shop.dto.response.product.ProductDtoResponse;
import com.cherniavskyi.shop.dto.query.ProductDtoQuery;
import com.cherniavskyi.shop.dto.search.ProductDtoSearchRequest;
import com.cherniavskyi.shop.entity.product.Category;
import com.cherniavskyi.shop.entity.product.Product;
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

    Product mapTo(ProductDtoResponse productDtoResponse);

    @Mapping(target = "name", source = "infix")
    ProductDtoQuery mapTo(ProductDtoSearchRequest productDtoSearchRequest);
}
