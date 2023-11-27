package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Size;
import com.cherniavskyi.shop.repository.product.SizeRepository;
import com.cherniavskyi.shop.service.product.SizeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class SizeServiceImpl implements SizeService {

    private final SizeRepository sizeRepository;

    @Override
    public Size create(Size size) {
        return sizeRepository.save(size);
    }

    @Override
    public Size read(Integer id) {
        return sizeRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Size with id:%s is not found", id)
                )
        );
    }

    @Override
    public Size update(Size size) {
        read(size.getId());
        return sizeRepository.save(size);
    }

    @Override
    public void delete(Integer id) {
        var size = read(id);
        sizeRepository.delete(size);
    }

    @Override
    public Page<Size> getAll(Pageable pageable) {
        return sizeRepository.findAll(pageable);
    }
}
