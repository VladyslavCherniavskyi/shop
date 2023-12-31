package com.cherniavskyi.shop.service.user;

import com.cherniavskyi.shop.entity.user.Role;

public interface RoleService {

    Role create(Role role);

    Role read(Integer id);

    Role update(Role role);

    void delete(Integer id);

}
