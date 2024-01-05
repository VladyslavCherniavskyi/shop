package com.cherniavskyi.shop.controller.user;

import com.cherniavskyi.shop.dto.request.user.update.CustomerDetailDtoUpdateRequest;
import com.cherniavskyi.shop.dto.response.user.CustomerDtoResponse;
import com.cherniavskyi.shop.facade.user.CustomerDetailFacade;
import com.cherniavskyi.shop.service.user.CustomerDetailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer_details")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
public class CustomerDetailController {

    private final CustomerDetailFacade customerDetailFacade;
    private final CustomerDetailService customerDetailService;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER') and @securityChecker.isIdMatch(#id)")
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> read(@PathVariable @Valid Long id) {
        return new ResponseEntity<>(customerDetailFacade.read(id), HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
    @PutMapping("/{id}")
    public ResponseEntity<CustomerDtoResponse> update(@PathVariable @Valid Long id,
                                                      @RequestBody @Valid CustomerDetailDtoUpdateRequest customerDetailDtoUpdateRequest) {
        return new ResponseEntity<>(customerDetailFacade.update(id, customerDetailDtoUpdateRequest), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable @Valid Long id) {
        customerDetailService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
