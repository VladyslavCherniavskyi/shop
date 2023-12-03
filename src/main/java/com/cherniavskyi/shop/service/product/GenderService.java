package com.cherniavskyi.shop.service.product;

import com.cherniavskyi.shop.entity.product.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GenderService {

    Gender create(Gender gender);

    Gender read(Integer id);

    Gender update(Gender gender);

    void delete(Integer id);

    Page<Gender> getAll(Pageable pageable);
}
