package com.cherniavskyi.shop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PasswordValidation implements ConstraintValidator<ValidPassword, String> {

    public String matcher(String password, String repeatPassword) {
        if (!Objects.equals(password, repeatPassword)) {
            throw new ValidationException("Passwords do not match. Please make sure your passwords match.");
        }
        return password;
    }

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$");
    }
}
