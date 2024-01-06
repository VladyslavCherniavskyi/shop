package com.cherniavskyi.shop.service.user;

import com.cherniavskyi.shop.entity.user.User;

public interface UserService {

    User create(User user);

    User read(Long id);

    User readByEmail(String email);

    User readByPhone(String phone);

    User update(User user);

}
