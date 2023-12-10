package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.request.product.create.BrandDtoCreateRequest;
import com.cherniavskyi.shop.dto.response.product.BrandDtoResponse;
import com.cherniavskyi.shop.facade.product.BrandFacade;
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
@RequestMapping("/brands")
@RequiredArgsConstructor
@Validated
public class BrandController {

    private final BrandFacade brandFacade;

    @GetMapping
    public ResponseEntity<Page<BrandDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(brandFacade.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Long id) {
        return new ResponseEntity<>(brandFacade.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandDtoResponse> create(@RequestBody @Valid BrandDtoCreateRequest brandDtoCreateRequest) {
        return new ResponseEntity<>(brandFacade.create(brandDtoCreateRequest), HttpStatus.OK);
    }

}
