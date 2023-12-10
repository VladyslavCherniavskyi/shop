package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.request.product.create.ColorDtoCreateRequest;
import com.cherniavskyi.shop.dto.response.product.ColorDtoResponse;
import com.cherniavskyi.shop.facade.product.ColorFacade;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/colors")
@RequiredArgsConstructor
@Validated
public class ColorController {

    private final ColorFacade colorFacade;

    @GetMapping
    public ResponseEntity<Page<ColorDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(colorFacade.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ColorDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        return new ResponseEntity<>(colorFacade.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ColorDtoResponse> create(@RequestBody @Valid ColorDtoCreateRequest colorDtoCreateRequest) {
        return new ResponseEntity<>(colorFacade.create(colorDtoCreateRequest), HttpStatus.OK);
    }

}
