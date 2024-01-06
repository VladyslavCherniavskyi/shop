package com.cherniavskyi.shop.dto.request.user.register;

import com.cherniavskyi.shop.dto.request.user.create.UserDtoCreateRequest;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public record CustomerRegisterDtoRequest(
        @Valid
        UserDtoCreateRequest userDtoCreateRequest
) {
}
