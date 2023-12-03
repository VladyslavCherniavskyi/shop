package com.cherniavskyi.shop.service.product;

import com.cherniavskyi.shop.entity.product.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {

    Brand create(Brand brand);

    Brand read(Long id);

    Brand update(Brand brand);

    void delete(Long id);

    Page<Brand> getAll(Pageable pageable);
}
