package com.cherniavskyi.shop.facade.product;

import com.cherniavskyi.shop.dto.request.product.create.SizeDtoCreateRequest;
import com.cherniavskyi.shop.dto.response.product.SizeDtoResponse;
import com.cherniavskyi.shop.mapper.ProductMapper;
import com.cherniavskyi.shop.service.product.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class SizeFacade {

    private final SizeService sizeService;
    private final ProductMapper productMapper;

    public Page<SizeDtoResponse> getAll(Pageable pageable) {
        return sizeService.getAll(pageable)
                .map(productMapper::mapTo);
    }

    public SizeDtoResponse read(Integer id) {
        var brand = sizeService.read(id);
        return productMapper.mapTo(brand);
    }

    public SizeDtoResponse create(SizeDtoCreateRequest sizeDtoCreateRequest) {
        var size = productMapper.mapFrom(sizeDtoCreateRequest);
        var createdSize = sizeService.create(size);
        return productMapper.mapTo(createdSize);
    }
}
