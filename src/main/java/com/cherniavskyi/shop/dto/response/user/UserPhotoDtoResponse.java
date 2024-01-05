package com.cherniavskyi.shop.dto.response.user;

import java.util.UUID;

public record UserPhotoDtoResponse(
        UUID id,
        String name,
        String url,
        String type,
        Long size,
        Long userId
) {
}