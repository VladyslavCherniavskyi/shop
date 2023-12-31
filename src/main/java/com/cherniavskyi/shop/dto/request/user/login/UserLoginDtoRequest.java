package com.cherniavskyi.shop.dto.request.user.login;

import jakarta.validation.constraints.NotBlank;

public record UserLoginDtoRequest(
        @NotBlank(message = "The 'email' cannot be empty") //TODO add regexp
        String email,
        @NotBlank(message = "The 'password' cannot be empty") //TODO ???add regexp
        String password
) {
}
