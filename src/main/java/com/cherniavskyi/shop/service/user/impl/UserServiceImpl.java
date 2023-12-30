package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.entity.user.User;
import com.cherniavskyi.shop.repository.user.UserRepository;
import com.cherniavskyi.shop.service.user.UserService;
import jakarta.persistence.EntityNotFoundException;
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

    @Override
    public User read(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("User with email:%s is not found", email)
                )
        );
    }

    @Override
    public User update(User user) {
        read(user.getId());
        return userRepository.save(user);
    }

}
