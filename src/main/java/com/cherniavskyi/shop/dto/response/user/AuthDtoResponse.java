package com.cherniavskyi.shop.dto.response.user;

public record AuthDtoResponse(
        String username,
        String jwtToken
) {
}
