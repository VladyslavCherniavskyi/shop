package com.cherniavskyi.shop.facade.product;

import com.cherniavskyi.shop.dto.response.product.ColorDtoResponse;
import com.cherniavskyi.shop.mapper.ProductMapper;
import com.cherniavskyi.shop.service.product.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ColorFacade {

    private final ColorService colorService;
    private final ProductMapper productMapper;

    public Page<ColorDtoResponse> getAll(Pageable pageable) {
        return colorService.getAll(pageable)
                .map(productMapper::mapTo);
    }

    public ColorDtoResponse read(Long id) {
        var brand = colorService.read(id);
        return productMapper.mapTo(brand);
    }
}
