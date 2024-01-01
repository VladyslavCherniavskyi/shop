package com.cherniavskyi.shop.dto.request.user.create;

import com.cherniavskyi.shop.entity.user.UserRole;
import jakarta.validation.constraints.NotNull;

public record RoleDtoCreateRequest(

        @NotNull(message = "Name cannot be null")
        UserRole name

) {
}
