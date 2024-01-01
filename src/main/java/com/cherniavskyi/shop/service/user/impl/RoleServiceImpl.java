package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.entity.user.Role;
import com.cherniavskyi.shop.entity.user.UserRole;
import com.cherniavskyi.shop.repository.user.RoleRepository;
import com.cherniavskyi.shop.service.user.RoleService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;


    @Override
    public Role create(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role read(Integer id) {
        return roleRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Role with id:%s is not found", id)
                )
        );
    }

    @Override
    public Role read(UserRole role) {
        return roleRepository.findByName(role).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Role with name:%s is not found", role.name())
                )
        );
    }

    @Override
    public Role update(Role role) {
        read(role.getId());
        return roleRepository.save(role);
    }

    @Override
    public void delete(Integer id) {
        var role = read(id);
        roleRepository.delete(role);
    }
}
