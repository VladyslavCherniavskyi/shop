package com.cherniavskyi.shop.repository.user;

import com.cherniavskyi.shop.entity.user.Role;
import com.cherniavskyi.shop.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(UserRole role);
}
