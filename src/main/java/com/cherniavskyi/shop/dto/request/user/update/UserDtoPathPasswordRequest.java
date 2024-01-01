package com.cherniavskyi.shop.dto.request.user.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserDtoPathPasswordRequest(

        @NotBlank(message = "Password cannot be empty")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
                message = "Password is invalid "
        )
        String oldPassword,

        @NotBlank(message = "Password cannot be empty")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
                message = "Password is invalid "
        )
        String newPassword,

        @NotBlank(message = "RepeatPassword cannot be empty")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
                message = "Password is invalid "
        )
        String repeatNewPassword

) {
}
