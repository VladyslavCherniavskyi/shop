package com.cherniavskyi.shop.repository.user;

import com.cherniavskyi.shop.entity.user.User;
import com.cherniavskyi.shop.entity.user.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, UserId> {
    User findByEmail(String email);
}
