package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.entity.user.User;
import com.cherniavskyi.shop.repository.user.UserRepository;
import com.cherniavskyi.shop.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public User create(User user) {
        verificationExists(user);
        return userRepository.save(user);
    }

    @Override
    public User read(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("User with id:%s is not found", id)
                )
        );
    }

    public User readByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("User with email:%s is not found", email)
                )
        );
    }

    @Override
    public User readByPhone(String phone) {
        return userRepository.findByPhone(phone).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("User with phone:%s is not found", phone)
                )
        );
    }

    @Override
    public User update(User user) {
        read(user.getId());
        return userRepository.save(user);
    }

    private void verificationExists(User user) {
        var existsByPhone = userRepository.existsByPhone(user.getPhone());
        if (existsByPhone) {
            throw new ValidationException(String.format("User with phone:%s already exists", user.getPhone()));
        }
        var existsByEmail = userRepository.existsByEmail(user.getEmail());
        if (existsByEmail) {
            throw new ValidationException(String.format("User with email:%s already exists", user.getEmail()));
        }
    }
}
