package com.cherniavskyi.shop.facade.product;

import com.cherniavskyi.shop.dto.request.product.create.ProductDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.product.update.ProductDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.product.ProductDtoResponse;
import com.cherniavskyi.shop.dto.search.ProductDtoFilterRequest;
import com.cherniavskyi.shop.dto.search.ProductDtoSearchRequest;
import com.cherniavskyi.shop.mapper.ProductMapper;
import com.cherniavskyi.shop.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ProductFacade {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public Page<ProductDtoResponse> getAll(Pageable pageable) {
        return productService.getAll(pageable)
                .map(productMapper::mapTo);
    }

    public ProductDtoResponse read(Long id) {
        var product = productService.read(id);
        return productMapper.mapTo(product);
    }

    public Page<ProductDtoResponse> searchByProductDtoSearch(ProductDtoSearchRequest productDtoSearchRequest, Pageable pageable) {
        var productDtoQuery = productMapper.mapTo(productDtoSearchRequest);
        return productService.findByDtoQuery(productDtoQuery, pageable)
                .map(productMapper::mapTo);
    }

    public Page<ProductDtoResponse> getAllByFilterDtoRequest(ProductDtoFilterRequest productDtoFilterRequest, Pageable pageable) {
        var filterDtoQuery = productMapper.mapTo(productDtoFilterRequest);
        return productService.findAllByFilterDtoQuery(filterDtoQuery, pageable)
                .map(productMapper::mapTo);
    }

    public ProductDtoResponse create(ProductDtoCreateRequest productDtoCreateRequest) {
        var product = productMapper.mapFrom(productDtoCreateRequest);
        var createdProduct = productService.create(product);
        return productMapper.mapTo(createdProduct);
    }

    public ProductDtoResponse update(Long id, ProductDtoUpdateRequest productDtoUpdateRequest) {
        var product = productMapper.mapFrom(productDtoUpdateRequest);
        product.setId(id);
        var updatedProduct = productService.update(product);
        return productMapper.mapTo(updatedProduct);
    }
}
