package com.cherniavskyi.shop.dto.request.user.update;

import com.cherniavskyi.shop.entity.user.UserRole;
import jakarta.validation.constraints.NotBlank;

public record RoleDtoUpdateRequest(

        @NotBlank(message = "RoleName cannot be empty")
        UserRole role

) {
}
