package com.cherniavskyi.shop.service.product;

import com.cherniavskyi.shop.entity.product.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ColorService {

    Color create(Color color);

    Color read(Long id);

    Color update(Color color);

    void delete(Long id);

    Page<Color> getAll(Pageable pageable);
}
