package com.cherniavskyi.shop.service.user;

import com.cherniavskyi.shop.entity.user.Role;
import com.cherniavskyi.shop.entity.user.UserRole;

public interface RoleService {

    Role create(Role role);

    Role read(Integer id);

    Role read(UserRole role);

    Role update(Role role);

    void delete(Integer id);

}
