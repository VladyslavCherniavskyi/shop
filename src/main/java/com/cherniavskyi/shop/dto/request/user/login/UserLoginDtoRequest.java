package com.cherniavskyi.shop.dto.request.user.login;

import com.cherniavskyi.shop.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserLoginDtoRequest(
        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format. Please provide a valid email address.")
        String email,

        @ValidPassword(message = "Please provide a valid password")
        String password
) {
}
