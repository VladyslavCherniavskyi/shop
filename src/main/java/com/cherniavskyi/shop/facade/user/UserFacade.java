package com.cherniavskyi.shop.facade.user;

import com.cherniavskyi.shop.dto.request.user.update.UserDtoPathPasswordRequest;
import com.cherniavskyi.shop.security.service.impl.PasswordService;
import com.cherniavskyi.shop.service.user.UserService;
import com.cherniavskyi.shop.validation.PasswordValidation;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;
    private final PasswordService passwordService;
    private final PasswordValidation passwordValidation;

    public String patchPassword(Long id, UserDtoPathPasswordRequest userDtoPathPasswordRequest) {
        var user = userService.read(id);
        var match = passwordService.matches(
                userDtoPathPasswordRequest.oldPassword(),
                user.getPasswordHash()
        );

        if (!match) {
            throw new BadCredentialsException("Old password is incorrect");
        }

        var rawPassword = passwordValidation.matcher(
                userDtoPathPasswordRequest.newPassword(),
                userDtoPathPasswordRequest.repeatNewPassword()
        );
        var encodedPassword = passwordService.encodePassword(rawPassword);
        user.setPasswordHash(encodedPassword);
        return Try.of(() -> userService.update(user))
                .map(u -> "Password changed successfully for user: " + u.getEmail())
                .get();
    }
}
