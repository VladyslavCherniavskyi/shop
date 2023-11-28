package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.response.product.GenderDtoResponse;
import com.cherniavskyi.shop.facade.product.GenderFacade;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/genders")
@RequiredArgsConstructor
@Validated
public class GenderController {

    private final GenderFacade genderFacade;

    @GetMapping
    public ResponseEntity<Page<GenderDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(genderFacade.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenderDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Integer id) {
        return new ResponseEntity<>(genderFacade.read(id), HttpStatus.OK);
    }

}
