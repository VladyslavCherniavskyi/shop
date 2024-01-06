package com.cherniavskyi.shop.service.user.impl;

import com.cherniavskyi.shop.entity.user.employee.EmployeeDetail;
import com.cherniavskyi.shop.repository.user.EmployeeDetailRepository;
import com.cherniavskyi.shop.service.user.EmployeeDetailService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeDetailServiceImpl implements EmployeeDetailService {

    private final EmployeeDetailRepository employeeDetailRepository;

    @Override
    public EmployeeDetail create(EmployeeDetail employeeDetail) {
        return employeeDetailRepository.save(employeeDetail);
    }

    @Override
    public EmployeeDetail read(Long id) {
        return employeeDetailRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("Employee with id:%s is not found", id)
                )
        );
    }

    @Override
    public EmployeeDetail update(EmployeeDetail employeeDetail) {
        read(employeeDetail.getUserId());
        return employeeDetailRepository.save(employeeDetail);
    }

    @Override
    public void delete(Long id) {
        var employeeDetail = read(id);
        employeeDetailRepository.delete(employeeDetail);
    }
}
