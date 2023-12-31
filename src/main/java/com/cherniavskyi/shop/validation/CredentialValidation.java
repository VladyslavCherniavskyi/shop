package com.cherniavskyi.shop.validation;

import com.cherniavskyi.shop.entity.user.UserGender;
import com.cherniavskyi.shop.util.TimeUtils;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class CredentialValidation {

    public String userGender(UserGender userGender) {
        return Optional.ofNullable(Arrays.stream(UserGender.values())
                        .filter(gender -> Objects.equals(gender, userGender))
                        .map(Enum::name)
                        .collect(Collectors.joining()))
                .orElseThrow(
                        () -> new ValidationException(
                                String.format(
                                        "Gender: %s is invalid", userGender
                                )
                        )
                );
    }

    public Date dateOfBirthForCustomer(Date dateOfBirth) {
        var limitYears = 14;

        Period period = Period.between(
                toLocalDate(dateOfBirth),
                LocalDate.now()
        );
        var date14YearsAgo = period.minusYears(limitYears).normalized();

        if (period.minusYears(limitYears).isNegative()) {
            throw new ValidationException(
                    String.format(
                            "DataOfBirth: %s is invalid. Data should be more then: %s ",
                            TimeUtils.formatter(dateOfBirth), date14YearsAgo
                    )
            );
        }
        return dateOfBirth;
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
    }

    public static void main(String[] args) {
        var c = new CredentialValidation();
        var now = new Date();

        @Deprecated
        var bd = new Date(110, Calendar.MARCH, 03);
        var bdLocal = LocalDate.of(2003, 12, 31);
        var year = now.before(bd);
        System.out.println(
                c.dateOfBirthForCustomer(bd)
        );
        System.out.println(year);


    }

}