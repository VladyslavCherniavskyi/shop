package com.cherniavskyi.shop.dto.request.user.update;

import com.cherniavskyi.shop.entity.user.employee.EmployeePosition;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record EmployeeDetailDtoUpdateRequest(

        @NotNull(message = "Position cannot be null")
        EmployeePosition position,

        @DecimalMin(value = "0.0", message = "Salary should be greater than or equal to 0")
        BigDecimal salary

) {
}
