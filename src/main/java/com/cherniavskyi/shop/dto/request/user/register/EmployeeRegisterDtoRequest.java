package com.cherniavskyi.shop.dto.request.user.register;

import com.cherniavskyi.shop.dto.request.user.create.UserDtoCreateRequest;
import com.cherniavskyi.shop.entity.user.employee.EmployeePosition;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Date;

public record EmployeeRegisterDtoRequest(
        UserDtoCreateRequest userDtoCreateRequest,

        @NotBlank(message = "Position cannot be empty")
        EmployeePosition position,

        @DecimalMin(value = "0.0", message = "Salary should be greater than or equal to 0")
        BigDecimal salary,

        @NotNull(message = "HireDate cannot be empty")
        Date hireDate

) {
}
