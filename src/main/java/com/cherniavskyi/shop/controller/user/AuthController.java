package com.cherniavskyi.shop.controller.user;

import com.cherniavskyi.shop.dto.request.user.login.UserLoginDtoRequest;
import com.cherniavskyi.shop.dto.request.user.register.CustomerRegisterDtoRequest;
import com.cherniavskyi.shop.dto.request.user.register.EmployeeRegisterDtoRequest;
import com.cherniavskyi.shop.dto.response.user.AuthDtoResponse;
import com.cherniavskyi.shop.facade.user.AuthFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/login")
    public ResponseEntity<AuthDtoResponse> login(@RequestBody @Valid UserLoginDtoRequest userLoginDtoRequest) {
        return ResponseEntity.ok(authFacade.login(userLoginDtoRequest));
    }

    @PostMapping("/customers/register")
    public ResponseEntity<AuthDtoResponse> registerCustomer(@RequestBody @Valid CustomerRegisterDtoRequest customerRegisterDtoRequest) {
        return ResponseEntity.ok(authFacade.registerCustomer(customerRegisterDtoRequest));
    }

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN')")
    @PostMapping("/employees/register")
    public ResponseEntity<AuthDtoResponse> registerEmployee(@RequestBody @Valid EmployeeRegisterDtoRequest employeeRegisterDtoRequest) {
        return ResponseEntity.ok(authFacade.registerEmployee(employeeRegisterDtoRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return new ResponseEntity<>("redirect:/auth/login", HttpStatus.OK);
    }

}
