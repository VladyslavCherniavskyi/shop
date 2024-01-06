package com.cherniavskyi.shop.dto.request.user.update;

import com.cherniavskyi.shop.entity.user.UserGender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.Date;

public record UserDtoUpdateRequest(

        @NotBlank(message = "FirstName cannot be empty")
        String firstName,

        @NotBlank(message = "LastName cannot be empty")
        String lastName,

        @NotBlank(message = "Gender cannot be empty")
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
        String email

) {
}
