package com.cherniavskyi.shop.dto.response.user;

import com.cherniavskyi.shop.entity.user.UserRole;

public record RoleDtoResponse(
        Integer id,
        UserRole name
) {
}
