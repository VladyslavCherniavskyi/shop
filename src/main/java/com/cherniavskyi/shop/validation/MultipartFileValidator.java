package com.cherniavskyi.shop.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public class MultipartFileValidator implements
        ConstraintValidator<ValidMultipartFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return Optional.ofNullable(file)
                .filter(multipartFile -> !multipartFile.isEmpty())
                .map(MultipartFile::getOriginalFilename)
                .filter(StringUtils::hasText)
                .isPresent();
    }
}
