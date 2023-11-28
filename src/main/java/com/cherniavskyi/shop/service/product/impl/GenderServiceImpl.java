package com.cherniavskyi.shop.service.product.impl;

import com.cherniavskyi.shop.entity.product.Gender;
import com.cherniavskyi.shop.repository.product.GenderRepository;
import com.cherniavskyi.shop.service.product.GenderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {

    private final GenderRepository genderRepository;

    @Override
    public Gender create(Gender gender) {
        return genderRepository.save(gender);
    }

    @Override
    public Gender read(Integer id) {
        return genderRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Gender with id:%s is not found", id)
                )
        );
    }

    @Override
    public Gender update(Gender gender) {
        read(gender.getId());
        return genderRepository.save(gender);
    }

    @Override
    public void delete(Integer id) {
        var gender = read(id);
        genderRepository.delete(gender);

    }

    @Override
    public Page<Gender> getAll(Pageable pageable) {
        return genderRepository.findAll(pageable);
    }
}
