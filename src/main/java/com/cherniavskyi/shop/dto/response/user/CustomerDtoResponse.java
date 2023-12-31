package com.cherniavskyi.shop.dto.response.user;

import java.util.Date;

public record CustomerDtoResponse(

        UserDtoResponse userDtoResponse,
        Date createAccount
) {
}
