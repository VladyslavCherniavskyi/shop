package com.cherniavskyi.shop.dto.request.user.create;

import com.cherniavskyi.shop.entity.user.UserGender;
import com.cherniavskyi.shop.validation.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record UserDtoCreateRequest(

        @NotBlank(message = "FirstName cannot be empty")
        String firstName,

        @NotBlank(message = "LastName cannot be empty")
        String lastName,

        @NotNull(message = "Gender cannot be null")
        UserGender gender,

        Date dateOfBirth,

        @NotBlank(message = "Address cannot be empty")
        String address,

        @NotBlank(message = "Phone cannot be empty")
        @Pattern(
                regexp = "^\\+?[0-9]*$",
                message = "Phone must be a valid numeric value, optionally starting with a '+'"
        )
        String phone,

        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Invalid email format. Please provide a valid email address.")
        String email,

        @ValidPassword(message = "Please provide a valid password")
        String password,

        @ValidPassword(message = "Please provide a valid password")
        String repeatPassword

) {
}
