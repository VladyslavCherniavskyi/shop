package com.cherniavskyi.shop.dto.response.user;

import com.cherniavskyi.shop.entity.user.UserGender;
import com.cherniavskyi.shop.entity.user.UserPhoto;

import java.util.Date;
import java.util.Set;

public record UserDtoResponse(

        Long id,
        String firstName,
        String lastName,
        UserGender gender,
        Date dateOfBirth,
        String address,
        String phone,
        String email,
        Set<RoleDtoResponse> roles,
        UserPhoto userPhoto,
        Set<Long> orderIds
) {
}
