package com.cherniavskyi.shop.service.product;

import com.cherniavskyi.shop.entity.product.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SizeService {

    Size create(Size size);

    Size read(Integer id);

    Size update(Size size);

    void delete(Integer id);

    Page<Size> getAll(Pageable pageable);
}
