package com.cherniavskyi.shop.dto.query;

import jakarta.validation.constraints.NotBlank;

public record ProductDtoQuery(

        @NotBlank(message = "Name cannot be empty") String name

) {
}
