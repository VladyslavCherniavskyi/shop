package com.cherniavskyi.shop.facade.product;

import com.cherniavskyi.shop.dto.response.product.GenderDtoResponse;
import com.cherniavskyi.shop.mapper.ProductMapper;
import com.cherniavskyi.shop.service.product.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class GenderFacade {

    private final GenderService genderService;
    private final ProductMapper productMapper;

    public Page<GenderDtoResponse> getAll(Pageable pageable) {
        return genderService.getAll(pageable)
                .map(productMapper::mapTo);
    }

    public GenderDtoResponse read(Integer id) {
        var brand = genderService.read(id);
        return productMapper.mapTo(brand);
    }
}
