package com.cherniavskyi.shop.controller.user;

import com.cherniavskyi.shop.dto.request.user.update.UserDtoPathPasswordRequest;
import com.cherniavskyi.shop.dto.response.order.OrderDtoResponse;
import com.cherniavskyi.shop.facade.user.UserFacade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PreAuthorize("hasAnyRole('SUPER_ADMIN','ADMIN','EMPLOYEE', 'CUSTOMER')")
    @GetMapping("/{id}/orders")
    public ResponseEntity<Page<OrderDtoResponse>> getAllOrdersByUserId(
            @PathVariable @NotNull(message = "Id cannot be null") Long id,
            Pageable pageable) {
        return new ResponseEntity<>(userFacade.getAllOrdersByUserId(id, pageable), HttpStatus.OK);
    }

}
