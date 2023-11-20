package com.cherniavskyi.shop.service.product;

import com.cherniavskyi.shop.entity.product.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    Category create(Category product);

    Category read(Integer id);

    Category update(Category product);

    void delete(Integer id);

    Page<Category> getAll(Pageable pageable);

    Category readByName(String name);
}
