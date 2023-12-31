package com.cherniavskyi.shop.dto.request.user.create;

import com.cherniavskyi.shop.entity.user.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RoleDtoCreateRequest(

        @NotBlank(message = "Name cannot be empty")
        UserRole name

) {
}
