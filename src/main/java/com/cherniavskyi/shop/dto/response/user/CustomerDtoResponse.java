package com.cherniavskyi.shop.dto.response.user;

public record CustomerDtoResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address
) {
}
