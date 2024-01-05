package com.cherniavskyi.shop.mapper;

import com.cherniavskyi.shop.dto.request.user.create.RoleDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.user.create.UserDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.user.register.CustomerRegisterDtoRequest;
import com.cherniavskyi.shop.dto.request.user.register.EmployeeRegisterDtoRequest;
import com.cherniavskyi.shop.dto.request.user.update.CustomerDetailDtoUpdateRequest;
import com.cherniavskyi.shop.dto.request.user.update.EmployeeDetailDtoUpdateRequest;
import com.cherniavskyi.shop.dto.request.user.update.RoleDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.CustomerDtoResponse;
import com.cherniavskyi.shop.dto.response.user.EmployeeDtoResponse;
import com.cherniavskyi.shop.dto.response.user.RoleDtoResponse;
import com.cherniavskyi.shop.dto.response.user.UserDtoResponse;
import com.cherniavskyi.shop.entity.user.Role;
import com.cherniavskyi.shop.entity.user.User;
import com.cherniavskyi.shop.entity.user.customer.CustomerDetail;
import com.cherniavskyi.shop.entity.user.employee.EmployeeDetail;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserMapper {

    CustomerDtoResponse mapTo(CustomerDetail customerDetail);

    CustomerDetail mapFrom(CustomerDetailDtoUpdateRequest customerDetailDtoUpdateRequest);

    CustomerDetail mapFrom(CustomerRegisterDtoRequest customerRegisterDtoRequest);

    User mapFrom(UserDtoCreateRequest userDtoCreateRequest);

    RoleDtoResponse mapTo(Role role);

    Role mapFrom(RoleDtoCreateRequest roleDtoCreateRequest);

    Role mapFrom(RoleDtoUpdateRequest roleDtoUpdateRequest);

    UserDtoResponse mapTo(User user);

    CustomerDtoResponse mapTo(UserDtoResponse userDtoResponse, CustomerDetail customerDetail);

    EmployeeDtoResponse mapTo(UserDtoResponse userDtoResponse, EmployeeDetail employeeDetail);

    EmployeeDetail mapFrom(EmployeeDetailDtoUpdateRequest detailDtoUpdateRequest);

    EmployeeDtoResponse mapTo(EmployeeDetail updatedemployeeDetail);

    EmployeeDetail mapFrom(EmployeeRegisterDtoRequest employeeRegisterDtoRequest);

}
