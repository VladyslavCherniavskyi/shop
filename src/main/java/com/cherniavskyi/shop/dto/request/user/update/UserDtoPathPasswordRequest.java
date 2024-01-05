package com.cherniavskyi.shop.dto.request.user.update;

import com.cherniavskyi.shop.validation.ValidPassword;

public record UserDtoPathPasswordRequest(

        @ValidPassword(message = "Please provide a valid password")
        String oldPassword,

        @ValidPassword(message = "Please provide a valid password")
        String newPassword,

        @ValidPassword(message = "Please provide a valid password")
        String repeatNewPassword

) {
}
