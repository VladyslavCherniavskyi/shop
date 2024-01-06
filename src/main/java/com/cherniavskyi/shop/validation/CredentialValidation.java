package com.cherniavskyi.shop.validation;

import com.cherniavskyi.shop.entity.user.UserGender;
import com.cherniavskyi.shop.util.TimeUtils;
import io.vavr.control.Try;
import jakarta.validation.ValidationException;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class CredentialValidation {

    public String userGender(UserGender userGender) {
        return Try.of(() -> Arrays.stream(UserGender.values())
                        .filter(gender -> Objects.equals(gender, userGender))
                        .map(Enum::name)
                        .collect(Collectors.joining()))
                .recover(IllegalArgumentException.class,
                        e -> handleError(
                                String.format(
                                        "Gender: %s is invalid. Error: %s", userGender, e.getMessage()
                                )
                        )
                ).get();

    }

    public Date dateOfBirthForCustomer(Date dateOfBirth) {
        return dataLimiteValidate(dateOfBirth, 14);
    }

    public Date dateOfBirthForEmployee(Date dateOfBirth) {
        return dataLimiteValidate(dateOfBirth, 18);
    }

    private Date dataLimiteValidate(@NotNull(message = "Data cannot be null") Date date, int limitYears) {
        var now = LocalDate.now();

        Period period = Period.between(
                toLocalDate(date),
                now
        );
        var startDateUnLimit = now.minus(Period.ofYears(limitYears));

        if (period.minusYears(limitYears).isNegative() || period.minusYears(limitYears).isZero()) {
            throw new ValidationException(
                    String.format(
                            "DataOfBirth: %s is invalid. Data should be equal to or more than: %s ",
                            TimeUtils.formatter(date), startDateUnLimit
                    )
            );
        }
        return date;
    }

    private LocalDate toLocalDate(Date date) {
        return date.toInstant().atZone(TimeUtils.UTC.toZoneId()).toLocalDate();
    }

    private String handleError(String errorMessage) {
        throw new ValidationException(errorMessage);
    }
}