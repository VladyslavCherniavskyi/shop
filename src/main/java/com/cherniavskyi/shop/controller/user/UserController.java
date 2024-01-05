package com.cherniavskyi.shop.controller.user;

import com.cherniavskyi.shop.dto.request.user.update.UserDtoPathPasswordRequest;
import com.cherniavskyi.shop.facade.user.UserFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
public class UserController {

    private final UserFacade userFacade;

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER') and @securityChecker.isIdMatch(#id)")
    @PatchMapping("/{id}")
    public ResponseEntity<String> patchPassword(@PathVariable @Valid Long id,
                                                @RequestBody @Valid UserDtoPathPasswordRequest userDtoPathPasswordRequest) {
        return new ResponseEntity<>(userFacade.patchPassword(id, userDtoPathPasswordRequest), HttpStatus.OK);
    }

}
