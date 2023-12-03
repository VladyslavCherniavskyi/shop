package com.cherniavskyi.shop.dto.search;

import jakarta.validation.constraints.Size;

public record ProductDtoSearchRequest(

        @Size(min = 3, message = "Infix should have a minimum length of 3 letters.")
        String infix

) {
}
