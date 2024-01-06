package com.cherniavskyi.shop.facade.user;

import com.cherniavskyi.shop.dto.request.user.create.RoleDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.user.update.RoleDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.RoleDtoResponse;
import com.cherniavskyi.shop.mapper.UserMapper;
import com.cherniavskyi.shop.service.user.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class RoleFacade {

    private final RoleService roleService;
    private final UserMapper userMapper;

    public RoleDtoResponse read(Integer id) {
        var role = roleService.read(id);
        return userMapper.mapTo(role);
    }

    public RoleDtoResponse create(RoleDtoCreateRequest roleDtoCreateRequest) {
        var role = userMapper.mapFrom(roleDtoCreateRequest);
        var createdRole = roleService.create(role);
        return userMapper.mapTo(createdRole);
    }

    public RoleDtoResponse update(Integer id, RoleDtoUpdateRequest roleDtoUpdateRequest) {
        var role = userMapper.mapFrom(roleDtoUpdateRequest);
        role.setId(id);
        var updatedRole = roleService.update(role);
        return userMapper.mapTo(updatedRole);
    }

}
