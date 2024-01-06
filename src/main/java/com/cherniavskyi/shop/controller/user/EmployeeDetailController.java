package com.cherniavskyi.shop.controller.user;

import com.cherniavskyi.shop.dto.request.user.update.EmployeeDetailDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.EmployeeDtoResponse;
import com.cherniavskyi.shop.facade.user.EmployeeDetailFacade;
import com.cherniavskyi.shop.service.user.EmployeeDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee_details")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasRole('SUPER_ADMIN')")
public class EmployeeDetailController {

    private final EmployeeDetailFacade employeeDetailFacade;
    private final EmployeeDetailService employeeDetailService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDtoResponse> read(@PathVariable @Valid Long id) {
        return new ResponseEntity<>(employeeDetailFacade.read(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE')")
    @PutMapping("/{id}")
    public ResponseEntity<EmployeeDtoResponse> update(@PathVariable @Valid Long id,
                                                      @RequestBody @Valid EmployeeDetailDtoUpdateRequest employeeDetailDtoUpdateRequest) {
        return new ResponseEntity<>(employeeDetailFacade.update(id, employeeDetailDtoUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Valid Long id) {
        employeeDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
