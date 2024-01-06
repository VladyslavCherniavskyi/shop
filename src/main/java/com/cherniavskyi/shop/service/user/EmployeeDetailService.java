package com.cherniavskyi.shop.service.user;

import com.cherniavskyi.shop.entity.user.employee.EmployeeDetail;

public interface EmployeeDetailService {

    EmployeeDetail create(EmployeeDetail employeeDetail);

    EmployeeDetail read(Long id);

    EmployeeDetail update(EmployeeDetail employeeDetail);

    void delete(Long id);
}
