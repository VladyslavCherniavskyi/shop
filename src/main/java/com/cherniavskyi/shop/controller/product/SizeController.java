package com.cherniavskyi.shop.controller.product;

import com.cherniavskyi.shop.dto.request.product.create.SizeDtoCreateRequest;
import com.cherniavskyi.shop.dto.response.product.SizeDtoResponse;
import com.cherniavskyi.shop.facade.product.SizeFacade;
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
@RequestMapping("/sizes")
@RequiredArgsConstructor
@Validated
public class SizeController {

    private final SizeFacade sizeFacade;

    @GetMapping
    public ResponseEntity<Page<SizeDtoResponse>> getAll(Pageable pageable) {
        return new ResponseEntity<>(sizeFacade.getAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SizeDtoResponse> read(@PathVariable @NotNull(message = "Id cannot be null") Integer id) {
        return new ResponseEntity<>(sizeFacade.read(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<SizeDtoResponse> create(@RequestBody @Valid SizeDtoCreateRequest sizeDtoCreateRequest) {
        return new ResponseEntity<>(sizeFacade.create(sizeDtoCreateRequest), HttpStatus.OK);
    }

}
