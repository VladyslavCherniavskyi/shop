package com.cherniavskyi.shop.facade.user;

import com.cherniavskyi.shop.dto.request.user.create.UserDtoCreateRequest;
import com.cherniavskyi.shop.dto.request.user.login.UserLoginDtoRequest;
import com.cherniavskyi.shop.dto.request.user.register.CustomerRegisterDtoRequest;
import com.cherniavskyi.shop.dto.request.user.register.EmployeeRegisterDtoRequest;
import com.cherniavskyi.shop.dto.response.user.AuthDtoResponse;
import com.cherniavskyi.shop.entity.user.User;
import com.cherniavskyi.shop.entity.user.UserRole;
import com.cherniavskyi.shop.mapper.UserMapper;
import com.cherniavskyi.shop.security.UserDetailsImpl;
import com.cherniavskyi.shop.security.service.JwtService;
import com.cherniavskyi.shop.security.service.impl.PasswordService;
import com.cherniavskyi.shop.service.user.CustomerDetailService;
import com.cherniavskyi.shop.service.user.EmployeeDetailService;
import com.cherniavskyi.shop.service.user.RoleService;
import com.cherniavskyi.shop.service.user.UserService;
import com.cherniavskyi.shop.validation.CredentialValidation;
import com.cherniavskyi.shop.validation.PasswordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@Transactional
@RequiredArgsConstructor
public class AuthFacade {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordService passwordService;
    private final UserService userService;
    private final RoleService roleService;
    private final CustomerDetailService customerDetailService;
    private final EmployeeDetailService employeeDetailService;
    private final UserMapper userMapper;
    private final PasswordValidation passwordValidation;
    private final CredentialValidation credentialValidation;


    public AuthDtoResponse login(UserLoginDtoRequest userLoginDtoRequest) {
        var authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDtoRequest.email(),
                        userLoginDtoRequest.password()
                )
        );
        var user = (UserDetailsImpl) authentication.getPrincipal();
        var jwt = jwtService.generateToken(user);
        return new AuthDtoResponse(
                user.getEmail(),
                jwt
        );
    }

    public AuthDtoResponse registerCustomer(CustomerRegisterDtoRequest customerRegisterDtoRequest) {
        credentialValidation.userGender(customerRegisterDtoRequest.userDtoCreateRequest().gender());
        var dataOfBirth = customerRegisterDtoRequest.userDtoCreateRequest().dateOfBirth();

        if (Objects.nonNull(dataOfBirth)) {
            credentialValidation.dateOfBirthForCustomer(dataOfBirth);
        }

        var roles = Stream.of(UserRole.CUSTOMER)
                .map(roleService::read)
                .collect(Collectors.toSet());

        var user = setterPasswordHash(customerRegisterDtoRequest.userDtoCreateRequest());
        user.setRoles(roles);
        var createdUser = userService.create(user);

        var customerDetail = userMapper.mapFrom(customerRegisterDtoRequest);
        customerDetail.setUser(createdUser);
        customerDetailService.create(customerDetail);
        return response(createdUser);
    }

    public AuthDtoResponse registerEmployee(EmployeeRegisterDtoRequest employeeRegisterDtoRequest) {
        credentialValidation.userGender(employeeRegisterDtoRequest.userDtoCreateRequest().gender());
        var dataOfBirth = employeeRegisterDtoRequest.userDtoCreateRequest().dateOfBirth();

        if (Objects.nonNull(dataOfBirth)) {
            credentialValidation.dateOfBirthForEmployee(dataOfBirth);
        }

        var roles = Stream.of(UserRole.EMPLOYEE)
                .map(roleService::read)
                .collect(Collectors.toSet());

        var user = setterPasswordHash(employeeRegisterDtoRequest.userDtoCreateRequest());
        user.setRoles(roles);
        var createdUser = userService.create(user);

        var employeeDetail = userMapper.mapFrom(employeeRegisterDtoRequest);
        employeeDetail.setUser(createdUser);
        employeeDetailService.create(employeeDetail);
        return response(createdUser);
    }

    private User setterPasswordHash(UserDtoCreateRequest userDtoCreateRequest) {
        var rawPassword = passwordValidation.matcher(
                userDtoCreateRequest.password(),
                userDtoCreateRequest.repeatPassword()
        );

        var user = userMapper.mapFrom(userDtoCreateRequest);
        var encodedPassword = passwordService.encodePassword(rawPassword);
        user.setPasswordHash(encodedPassword);
        return user;
    }

    private AuthDtoResponse response(User user) {
        var jwt = jwtService.generateToken(user);
        return new AuthDtoResponse(user.getEmail(), jwt);
    }

}
