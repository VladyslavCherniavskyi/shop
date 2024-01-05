package com.cherniavskyi.shop.facade.user;

import com.cherniavskyi.shop.dto.request.user.update.EmployeeDetailDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.EmployeeDtoResponse;
import com.cherniavskyi.shop.mapper.UserMapper;
import com.cherniavskyi.shop.service.user.EmployeeDetailService;
import com.cherniavskyi.shop.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class EmployeeDetailFacade {

    private final EmployeeDetailService employeeDetailService;
    private final UserService userService;
    private final UserMapper userMapper;

    public EmployeeDtoResponse read(Long id) {
        var user = userService.read(id);
        var userDtoResponse = userMapper.mapTo(user);
        return userMapper.mapTo(userDtoResponse, user.getEmployeeDetail());
    }

    public EmployeeDtoResponse update(Long id, EmployeeDetailDtoUpdateRequest detailDtoUpdateRequest) {
        var employeeDetail = userMapper.mapFrom(detailDtoUpdateRequest);
        employeeDetail.setUserId(id);
        var updatedemployeeDetail = employeeDetailService.update(employeeDetail);
        return userMapper.mapTo(updatedemployeeDetail);
    }
}
