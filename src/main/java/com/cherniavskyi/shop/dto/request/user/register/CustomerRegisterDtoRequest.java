package com.cherniavskyi.shop.dto.request.user.register;

import com.cherniavskyi.shop.dto.request.user.create.UserDtoCreateRequest;

public record CustomerRegisterDtoRequest(
        UserDtoCreateRequest userDtoCreateRequest
) {
}
