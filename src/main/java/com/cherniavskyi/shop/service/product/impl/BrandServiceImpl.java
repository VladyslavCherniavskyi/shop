package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Brand;
import com.cherniavskyi.shop.repository.product.BrandRepository;
import com.cherniavskyi.shop.service.product.BrandService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    @Override
    public Brand create(Brand brand) {
        return brandRepository.save(brand);
    }

    @Override
    public Brand read(Long id) {
        return brandRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Brand with id:%s is not found", id)
                )
        );
    }

    @Override
    public Brand update(Brand brand) {
        read(brand.getId());
        return brandRepository.save(brand);
    }

    @Override
    public void delete(Long id) {
        var brand = read(id);
        brandRepository.delete(brand);
    }

    @Override
    public Page<Brand> getAll(Pageable pageable) {
        return brandRepository.findAll(pageable);
    }
}
