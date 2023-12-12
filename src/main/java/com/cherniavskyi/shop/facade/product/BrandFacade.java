package com.cherniavskyi.shop.facade.product;

import com.cherniavskyi.shop.dto.request.product.create.BrandDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.product.update.BrandDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.product.BrandDtoResponse;
import com.cherniavskyi.shop.mapper.ProductMapper;
import com.cherniavskyi.shop.service.product.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class BrandFacade {

    private final BrandService brandService;
    private final ProductMapper productMapper;

    public Page<BrandDtoResponse> getAll(Pageable pageable) {
        return brandService.getAll(pageable)
                .map(productMapper::mapTo);
    }

    public BrandDtoResponse read(Long id) {
        var brand = brandService.read(id);
        return productMapper.mapTo(brand);
    }

    public BrandDtoResponse create(BrandDtoCreateRequest brandDtoCreateRequest) {
        var brand = productMapper.mapFrom(brandDtoCreateRequest);
        var createdBrand = brandService.create(brand);
        return productMapper.mapTo(createdBrand);
    }

    public BrandDtoResponse update(Long id, BrandDtoUpdateRequest brandDtoUpdateRequest) {
        var brand = productMapper.mapFrom(brandDtoUpdateRequest);
        brand.setId(id);
        var updatedBrand = brandService.update(brand);
        return productMapper.mapTo(updatedBrand);
    }
}
