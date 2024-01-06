package com.cherniavskyi.shop.dto.response.user;

import com.cherniavskyi.shop.entity.user.employee.EmployeePosition;

import java.math.BigDecimal;
import java.util.Date;

public record EmployeeDtoResponse(

        UserDtoResponse userDtoResponse,
        EmployeePosition position,
        BigDecimal salary,
        Date hireDate

) {
}
