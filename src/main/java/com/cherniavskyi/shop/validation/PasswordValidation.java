package com.cherniavskyi.shop.validation;

import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PasswordValidation {

    public String matcher(String password, String repeatPassword) {
        if (!Objects.equals(password, repeatPassword)) {
            throw new ValidationException("Passwords do not match. Please make sure your passwords match.");
        }
        return password;
    }

}
