package com.cherniavskyi.shop.facade.user;

import com.cherniavskyi.shop.dto.request.user.login.UserLoginDtoRequest;
import com.cherniavskyi.shop.dto.request.user.register.CustomerRegisterDtoRequest;
import com.cherniavskyi.shop.dto.request.user.register.EmployeeRegisterDtoRequest;
import com.cherniavskyi.shop.dto.response.user.AuthDtoResponse;
import com.cherniavskyi.shop.entity.user.UserRole;
import com.cherniavskyi.shop.mapper.UserMapper;
import com.cherniavskyi.shop.security.UserDetailsImpl;
import com.cherniavskyi.shop.security.service.JwtService;
import com.cherniavskyi.shop.security.service.impl.PasswordService;
import com.cherniavskyi.shop.service.user.CustomerService;
import com.cherniavskyi.shop.service.user.RoleService;
import com.cherniavskyi.shop.service.user.UserService;
import com.cherniavskyi.shop.validation.CredentialValidation;
import com.cherniavskyi.shop.validation.PasswordValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    private final CustomerService customerService;
    //    private final EmployeeService employeeService;
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
        //validation
        credentialValidation.userGender(customerRegisterDtoRequest.userDtoCreateRequest().gender());
        if (Objects.nonNull(customerRegisterDtoRequest.userDtoCreateRequest().dateOfBirth())) {
            credentialValidation.dateOfBirthForCustomer(customerRegisterDtoRequest.userDtoCreateRequest().dateOfBirth());
        }

        var roles = Stream.of(UserRole.CUSTOMER)
                .map(roleService::read)
                .collect(Collectors.toSet());

        var rawPassword = passwordValidation.matcher(
                customerRegisterDtoRequest.userDtoCreateRequest().password(),
                customerRegisterDtoRequest.userDtoCreateRequest().repeatPassword()
        );

        //mapping
        var customerDetail = userMapper.mapFrom(customerRegisterDtoRequest);
        var user = userMapper.mapFrom(customerRegisterDtoRequest.userDtoCreateRequest());

        //setting and save to database
        var encodedPassword = passwordService.encodePassword(rawPassword);
        user.setRoles(roles);
        user.setPasswordHash(encodedPassword);

        var createdUser = userService.create(user);
        customerDetail.setUser(createdUser);
        customerDetail.setCreateAccount(new Date()); //TODO change automatically create Date
        customerService.create(customerDetail);

        var jwt = jwtService.generateToken(createdUser);
        return new AuthDtoResponse(createdUser.getEmail(), jwt);
    }

    public AuthDtoResponse registerEmployee(EmployeeRegisterDtoRequest employeeRegisterDtoRequest) {
        return null;
    }

}
