package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Color;
import com.cherniavskyi.shop.repository.product.ColorRepository;
import com.cherniavskyi.shop.service.product.ColorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public Color create(Color color) {
        return colorRepository.save(color);
    }

    @Override
    public Color read(Long id) {
        return colorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Color with id:%s is not found", id)
                )
        );
    }

    @Override
    public Color update(Color color) {
        read(color.getId());
        return colorRepository.save(color);
    }

    @Override
    public void delete(Long id) {
        var color = read(id);
        colorRepository.delete(color);
    }

    @Override
    public Page<Color> getAll(Pageable pageable) {
        return colorRepository.findAll(pageable);
    }
}
